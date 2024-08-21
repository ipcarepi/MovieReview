package MR.Movie.Review.Repository;

import MR.Movie.Review.member.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<String, Member> store = new HashMap<>();
    private static String sequence = "0";

    @Override
    public Member save(Member member) {
        if (member.getId() != null)
            store.put(member.getId(), member);
        else if (member.getName() != null) {
            store.put(member.getName(), member);
        }
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearstore() {
        store.clear();
    }
}
