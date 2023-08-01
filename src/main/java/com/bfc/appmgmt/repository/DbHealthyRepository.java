package com.bfc.appmgmt.repository;

import com.bfc.appmgmt.domain.DbHealthy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * packageName    : com.bfc.appmgmt.repository
 * fileName       : DbHealthyRepository
 * author         : joyousang
 * date           : 2023/08/01
 * description    :
 */
public interface DbHealthyRepository extends JpaRepository<DbHealthy, Long> {
}
