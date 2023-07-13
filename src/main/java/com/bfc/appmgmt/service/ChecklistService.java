package com.bfc.appmgmt.service;

import com.bfc.appmgmt.domain.Checklist;
import com.bfc.appmgmt.domain.Member;
import com.bfc.appmgmt.dto.SearchChecklistDto;
import com.bfc.appmgmt.exception.UnauthorizedException;
import com.bfc.appmgmt.repository.CheckListRepository;
import com.bfc.appmgmt.repository.ChecklistSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
    private final MemberService memberService;
    private final CheckListRepository checkListRepository;
    private final ChecklistSearchRepository checklistSearchRepository;

    public List<SearchChecklistDto> searchChecklist(String authKey) {
        Member findMember = memberService.findByAuthKey(authKey);
        return checklistSearchRepository.searchWithItemsCount(findMember.getId());
    }

    /**
     * 체크리스트 생성
     *
     * @param title
     * @param authKey
     * @return checklistId
     */
    @Transactional
    public Long createChecklist(String authKey, String title) {
        Member findMember = memberService.findByAuthKey(authKey);
        Checklist checklist = Checklist.builder()
                .title(title)
                .member(findMember)
                .build();
        checkListRepository.save(checklist);
        return checklist.getId();
    }

    @Transactional
    public Long updateChecklist(String authKey, Long checklistId, String title) {
        Checklist findChecklist = findChecklistAndValidateOwner(authKey, checklistId);
        findChecklist.updateTitle(title);
        return findChecklist.getId();
    }

    @Transactional
    public Long deleteChecklist(String authKey, Long checklistId) {
        Checklist findChecklist = findChecklistAndValidateOwner(authKey, checklistId);
        checkListRepository.delete(findChecklist);
        return checklistId;
    }

    public Checklist findChecklistAndValidateOwner(String authKey, Long checklistId) {
        Member findMember = memberService.findByAuthKey(authKey);
        Checklist findChecklist = checkListRepository.findById(checklistId)
                .orElseThrow(() -> new EntityNotFoundException("해당 체크리스트를 찾을 수 없습니다."));
        validateChecklistOwner(findChecklist, findMember);
        return findChecklist;
    }

    private void validateChecklistOwner(Checklist findChecklist, Member findMember) {
        if (!findMember.equals(findChecklist.getMember())) {
            throw new UnauthorizedException("잘못된 접근입니다.");
        }
    }
}