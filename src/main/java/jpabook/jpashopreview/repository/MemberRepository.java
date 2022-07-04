package jpabook.jpashopreview.repository;

import jpabook.jpashopreview.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext // SpringBoot가 자동으로 EM을 생성해줌
    private EntityManager em;


    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
