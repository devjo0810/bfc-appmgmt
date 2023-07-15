package com.bfc.appmgmt.repository;

import com.bfc.appmgmt.domain.ChecklistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName    : com.bfc.appmgmt.repository
 * fileName       : ChecklistItemRepository
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long> {
    List<ChecklistItem> findAllByIdIn(List<Long> ids);
    List<ChecklistItem> findAllByIdInAndChecklistId(List<Long> ids, Long checklistId);
}
