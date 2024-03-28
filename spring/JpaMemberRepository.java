package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA는 EntityManager로 관리 되기 때문에 EntityManager을 주입받아야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    // 중복되지 않는 것은 JPA로만 가능하지만,
    @Override
    public Member save(Member member) {
        em.persist(member); // persist() 영구 저장을 할때 쓰임
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 어떤 클래스에서 어떠한 식별자로 찾을 것인지
        return Optional.ofNullable(member);
    }

    // 중복되는 것은 JPQL을 사용해야 한다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // 객체에서 객체 전체을 찾아 전체 결과을 반환해
    }
}
