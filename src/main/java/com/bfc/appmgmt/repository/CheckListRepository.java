package com.bfc.appmgmt.repository;

import com.bfc.appmgmt.domain.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName    : com.bfc.appmgmt.repository
 * fileName       : CheckListRepository
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
public interface CheckListRepository extends JpaRepository<Checklist, Long> {
    List<Checklist> findByTitle(String title);
}
