package com.bfc.appmgmt.api.checklist;

import com.bfc.appmgmt.api.checklist.dto.SaveChecklistItemDto;
import com.bfc.appmgmt.api.checklist.dto.SearchChecklistItemDto;
import com.bfc.appmgmt.dto.ApiResponse;
import com.bfc.appmgmt.service.ChecklistItemService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * packageName    : com.bfc.appmgmt.api.checklist
 * fileName       : ChecklistItemApiController
 * author         : joyousang
 * date           : 2023/07/13
 * description    :
 */
@RestController
@RequiredArgsConstructor
public class ChecklistItemApiController {
    private final ChecklistItemService checklistItemService;

    @GetMapping("/api/checklist/{checklistId}/items")
    public ApiResponse getChecklistItems(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authKey,
                                         @PathVariable(value = "checklistId") Long checklistId) {
        List<SearchChecklistItemDto> searchItems = checklistItemService.search(authKey, checklistId);
        return ApiResponse.builder()
                .contents(searchItems)
                .count(searchItems.stream().count())
                .build();
    }

    @PostMapping("/api/checklist/{checklistId}/items")
    public ApiResponse saveChecklistItems(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authKey,
                                          @PathVariable(value = "checklistId") Long checklistId,
                                          @RequestBody @Valid List<SaveChecklistItemDto> checklistItems) {
        SaveChecklistItemsResponse saveChecklistItemsResponse = checklistItemService.saveItems(authKey, checklistId, checklistItems);
        return ApiResponse.builder().contents(saveChecklistItemsResponse).build();
    }

    @DeleteMapping("/api/checklist/{checklistId}/items/{itemId}")
    public ApiResponse deleteChecklistItem(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authKey,
                                           @PathVariable(value = "checklistId") Long checklistId,
                                           @PathVariable(value = "itemId") Long itemId) {
        return null;
    }

    @Data
    @Builder
    public static class SaveChecklistItemsResponse {
        private int createCount;
        private int updateCount;
    }
}