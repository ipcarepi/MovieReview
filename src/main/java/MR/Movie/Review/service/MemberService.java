package MR.Movie.Review.service;

import MR.Movie.Review.Repository.MemberRepository;
import MR.Movie.Review.Repository.MemoryMemberRepository;
import MR.Movie.Review.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public String join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        if (member.getId() != null) {
            return member.getId();
        } else if (member.getName() != null) {
            return member.getName();
        }

        return null;
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
            throw new IllegalStateException("Already exist");
        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId) {
        return memberRepository.findById(memberId);
    }

}
