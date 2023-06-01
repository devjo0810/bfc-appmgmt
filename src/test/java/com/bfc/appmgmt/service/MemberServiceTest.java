package com.bfc.appmgmt.service;

import com.bfc.appmgmt.domain.Member;
import com.bfc.appmgmt.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.bfc.appmgmt.service
 * fileName       : MemberServiceTest
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입(){
        //given
        String name = "user1";
        String email = "test@test.com";
        String password = "asdf1234";

        //when
        Long joinId = memberService.join(name, email, password);

        //then
        Member findMember = memberRepository.findById(joinId).get();
        assertEquals(name, findMember.getUsername());
        assertEquals(email, findMember.getEmail());
    }

    @Test
    public void 회원가입_중복된이메일(){
        //given
        String name = "user1";
        String email = "test@test.com";
        String password = "asdf1234";

        //when
        Long joinId = memberService.join(name, email, password);

        //then
        assertThrows(DuplicateKeyException.class,
                () -> memberService.join("user2", email, "1234asdf"));
    }

    @Test
    public void 비밀번호암호화확인(){
        //given
        String name = "user1";
        String email = "test@test.com";
        String password = "asdf1234";
        Long joinId = memberService.join(name, email, password);

        em.flush();
        em.clear();

        //when
        boolean isPasswordMatch = memberService.isPasswordMatch(joinId, password);

        //then
        assertEquals(isPasswordMatch, true);
    }

    @Test
    public void 비밀번호암호화확인_잘못된비밀번호(){
        //given
        String name = "user1";
        String email = "test@test.com";
        String password = "asdf1234";
        String wrongPassword = "4321fdsa";
        Long joinId = memberService.join(name, email, password);

        em.flush();
        em.clear();

        //when
        boolean isPasswordMatch = memberService.isPasswordMatch(joinId, wrongPassword);

        //then
        assertEquals(isPasswordMatch, false);
    }

}