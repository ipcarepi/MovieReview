package MR.Movie.Review.service;

import MR.Movie.Review.Repository.MemberRepository;
import MR.Movie.Review.Repository.MemoryMemberRepository;
import MR.Movie.Review.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearstore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        String saveName = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveName).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void exceptionForduplication() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("Already exist");

        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Already exist");
        }*/

        //then


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}