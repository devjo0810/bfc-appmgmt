package com.bfc.appmgmt.service;

import com.bfc.appmgmt.domain.Checklist;
import com.bfc.appmgmt.repository.CheckListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.bfc.appmgmt.service
 * fileName       : ChecklistService
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChecklistService {
    private final CheckListRepository checkListRepository;

    /**
     * 체크리스트 생성
     * @param title
     * @return checklistId
     */
    public Long createChecklist(String title) {
        Checklist checklist = Checklist.builder().title(title).build();
        checkListRepository.save(checklist);
        return checklist.getId();
    }
}
