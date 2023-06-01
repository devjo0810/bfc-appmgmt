package com.bfc.appmgmt.service;

import com.bfc.appmgmt.domain.Member;
import com.bfc.appmgmt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final BCryptPasswordEncoder passwordEncoder;

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
                .password(passwordEncoder.encode(password))
                .build();
        memberRepository.save(member);
        return member.getId();
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
        return passwordEncoder.matches(password, findMember.getPassword());
    }
}
