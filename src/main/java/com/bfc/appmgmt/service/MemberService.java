package com.bfc.appmgmt.service;

import com.bfc.appmgmt.common.SessionManager;
import com.bfc.appmgmt.domain.Member;
import com.bfc.appmgmt.exception.PasswordNotMatchException;
import com.bfc.appmgmt.repository.MemberRepository;
import com.bfc.appmgmt.util.EncodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * packageName    : com.bfc.appmgmt.service
 * fileName       : MemberService
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    /**
     * 회원가입
     * @param name
     * @param email
     * @param password
     * @return memberId
     */
    @Transactional
    public Long join(String name, String email, String password) {
        validateDuplicationEmail(email); // 이메일 중복체크
        Member member = Member.builder()
                .username(name)
                .email(email)
                .password(EncodeUtil.encodePassword(password))
                .build();
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 로그인
     * @param email
     * @param password
     * @return login key
     */
    public String login(String email, String password) {
        Member member = Optional.ofNullable(memberRepository.findByEmail(email))
                .orElseThrow(() -> new EntityNotFoundException("일치하지 않는 이메일입니다."));
        boolean passwordMatch = isPasswordMatch(member.getId(), password);
        if (passwordMatch == false) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
        return sessionManager.login(member.getId());
    }

    private void validateDuplicationEmail(String email) {
        boolean existsByEmail = memberRepository.existsByEmail(email);
        if (existsByEmail) {
            throw new DuplicateKeyException("이미 가입된 이메일입니다.");
        }
    }

    /**
     * 비밀번호 확인
     * @param id
     * @param password
     * @return isPasswordMatch
     */
    public boolean isPasswordMatch(Long id, String password) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("찾을 수 없는 회원입니다.", 1));
        return EncodeUtil.matches(password, findMember.getPassword());
    }
}
