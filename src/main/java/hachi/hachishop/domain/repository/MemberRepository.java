package hachi.hachishop.domain.repository;

import hachi.hachishop.domain.item.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory emf;

    //insert
    public void save(Member member) {
        em.persist(member);
    }

    //단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //모두 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //이름 가지고 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
        .setParameter("name", name)
        .getResultList();
    }
}
