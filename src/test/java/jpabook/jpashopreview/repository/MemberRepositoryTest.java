package jpabook.jpashopreview.repository;

import jpabook.jpashopreview.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("MemberA");

        //when
        Long findId = memberRepository.save(member);

        //then
        Member findMember = memberRepository.find(findId);

        Assertions.assertThat(member).isEqualTo(findMember);
    }

}