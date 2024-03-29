package com.bfc.appmgmt.service;

import com.bfc.appmgmt.api.checklist.dto.SaveChecklistItemDto;
import com.bfc.appmgmt.api.checklist.dto.SearchChecklistItemDto;
import com.bfc.appmgmt.domain.Checklist;
import com.bfc.appmgmt.domain.ChecklistItem;
import com.bfc.appmgmt.repository.ChecklistItemRepository;
import com.bfc.appmgmt.repository.ChecklistItemSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.bfc.appmgmt.api.checklist.ChecklistItemApiController.SaveChecklistItemsResponse;

/**
 * packageName    : com.bfc.appmgmt.service
 * fileName       : ChecklistItemService
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChecklistItemService {
    private final ChecklistService checklistService;
    private final ChecklistItemRepository checklistItemRepository;
    private final ChecklistItemSearchRepository checklistItemSearchRepository;

    public List<SearchChecklistItemDto> search(String authKey, Long checklistId) {
        checklistService.findChecklistAndValidateOwner(authKey, checklistId);
        return checklistItemSearchRepository.search(checklistId);
    }

    @Transactional
    public SaveChecklistItemsResponse saveItems(String authKey, Long checklistId, List<SaveChecklistItemDto> checklistItems) {
        Checklist findChecklist = checklistService.findChecklistAndValidateOwner(authKey, checklistId);
        return SaveChecklistItemsResponse.builder()
                .createCount(createItems(findChecklist, checklistItems))
                .updateCount(updateItems(checklistId, checklistItems))
                .build();
    }

    @Transactional
    public void deleteItem(String authKey, Long checklistId, Long itemId) {
        checklistService.findChecklistAndValidateOwner(authKey, checklistId);
        validateChecklistItemsInChecklist(List.of(itemId), checklistId);
        ChecklistItem findChecklistItem = checklistItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("찾을 수 없는 체크리스트 항목입니다."));
        checklistItemRepository.delete(findChecklistItem);
    }

    @Transactional
    public void deleteItems(String authKey, Long checklistId, List<Long> itemIds) {
        checklistService.findChecklistAndValidateOwner(authKey, checklistId);
        validateChecklistItemsInChecklist(itemIds, checklistId);
        checklistItemRepository.deleteAllById(itemIds);
    }

    private void validateChecklistItemsInChecklist(List<Long> checklistItemIds, Long checklistId) {
        int realCount = checklistItemRepository.findAllByIdInAndChecklistId(checklistItemIds, checklistId).size();
        int requestCount = checklistItemIds.size();
        if (realCount != requestCount) {
            throw new IllegalArgumentException("처리할 수 없는 체크리스트 항목이 존재합니다.");
        }
    }

    private int createItems(Checklist checklist, List<SaveChecklistItemDto> items) {
        List<ChecklistItem> createItems = items.stream()
                .filter(SaveChecklistItemDto::isCreate)
                .map(item -> item.toEntity(checklist))
                .collect(Collectors.toList());
        checklistItemRepository.saveAll(createItems);
        return createItems.size();
    }

    private void updateItem(SaveChecklistItemDto item) {
        ChecklistItem findChecklistItem = checklistItemRepository.findById(item.getChecklistItemId())
                .orElseThrow(() -> new EntityNotFoundException("찾을 수 없는 체크리스트 항목입니다."));
        findChecklistItem.update(item);
    }

    private int updateItems(Long checklistId, List<SaveChecklistItemDto> items) {
        List<SaveChecklistItemDto> updateItems = items.stream()
                .filter(SaveChecklistItemDto::isUpdate)
                .collect(Collectors.toList());
        validateChecklistItemsInChecklist(
                updateItems.stream().map(SaveChecklistItemDto::getChecklistItemId).collect(Collectors.toList()),
                checklistId
        );
        updateItems.forEach(item -> updateItem(item));
        return updateItems.size();
    }

}