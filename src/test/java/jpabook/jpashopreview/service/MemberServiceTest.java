package jpabook.jpashopreview.service;

import jpabook.jpashopreview.domain.Member;
import jpabook.jpashopreview.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("MemberA");

        //when
        Long savedID = memberService.join(member);

        //then

        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(savedID));
    }

    @Test
    void 중복_회원_예외() throws Exception{
        //given

        //when

        //then
    }

}