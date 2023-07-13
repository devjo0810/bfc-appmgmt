package com.bfc.appmgmt.api.checklist;

import com.bfc.appmgmt.dto.ApiResponse;
import com.bfc.appmgmt.service.ChecklistService;
import com.bfc.appmgmt.util.HttpHeaderUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * packageName    : com.bfc.appmgmt.api.checklist
 * fileName       : ChecklistApiController
 * author         : joyousang
 * date           : 2023/07/02
 * description    :
 */
@RestController
@RequiredArgsConstructor
public class ChecklistApiController {
    private final ChecklistService checklistService;
    @GetMapping("/api/checklist")
    public ApiResponse getChecklistV1(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authKey) {
        return ApiResponse.builder()
                .contents(checklistService.searchChecklist(authKey))
                .build();
    }

    @PostMapping("/api/checklist")
    public ApiResponse createChecklist(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authKey,
                                       @RequestBody @Valid SaveChecklistRequest saveChecklistRequest) {
        Long checklistId = checklistService.createChecklist(authKey, saveChecklistRequest.getTitle());
        return ApiResponse.builder()
                .contents(checklistId)
                .build();
    }

    @PutMapping("/api/checklist/{checklistId}")
    public ApiResponse updateChecklist(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authKey,
                                       @PathVariable(required = true, name = "checklistId") Long checklistId,
                                       @RequestBody @Valid SaveChecklistRequest saveChecklistRequest) {
        Long updateChecklistId = checklistService.updateChecklist(authKey, checklistId, saveChecklistRequest.getTitle());
        return ApiResponse.builder()
                .contents(updateChecklistId)
                .build();
    }

    @DeleteMapping("/api/checklist/{checklistId}")
    public ApiResponse deleteChecklist(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authKey,
                                       @PathVariable(required = true, name = "checklistId") Long checklistId) {
        Long deleteChecklistId = checklistService.deleteChecklist(authKey, checklistId);
        return ApiResponse.builder()
                .contents(deleteChecklistId)
                .build();
    }

    @Data
    static class SaveChecklistRequest {
        @NotEmpty(message = "체크리스트 타이틀이 비어있습니다.")
        private String title;
    }

}
