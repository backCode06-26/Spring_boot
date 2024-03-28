package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


// implements는 인터페이스을 불러오는거임
@Repository // Spring이 작동될때 Repositroy라고 Spring컨테이너에 넣어준다.
public class MemoryMemberRepository implements MemberRepository {

    // Map<key, value>와 같은 형태을 띤다.
    // HashMap<>() 해시 함수을 사용하여 데이터을 검색하느데 뛰어난 성능을 가지고 있기 때문에 사용한다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    // 해당되는 값이 있다면, 해당되는 값을 반환하고 null이면, Optional을 반환한다.
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // findAny()는 병렬처리을 하여 쓰레드가 먼저 발견한것을 반환한다, 순서는 보장하기 않음
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
