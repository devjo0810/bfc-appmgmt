package com.bfc.appmgmt.repository;

import com.bfc.appmgmt.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.bfc.appmgmt.repository
 * fileName       : MemberRepository
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Member findByEmail(String email);
}
