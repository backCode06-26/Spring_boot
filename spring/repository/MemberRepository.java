package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 추상메서드
    Optional<Member> findById(Long id); //null이 들어가도 되는 데이터타입
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 순서을 지키며, 중복을 허용하는 데이터타입
    void clearStore();
}
