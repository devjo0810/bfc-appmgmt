package com.bfc.appmgmt.api.checklist;

import com.bfc.appmgmt.dto.ApiResponse;
import com.bfc.appmgmt.service.ChecklistService;
import com.bfc.appmgmt.util.HttpHeaderUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse getChecklistV1(@RequestHeader HttpHeaders httpHeaders) {
        String authKey = HttpHeaderUtil.getAuthKey(httpHeaders);
        return ApiResponse.builder()
                .contents(checklistService.searchChecklist(authKey))
                .build();
    }

    @PostMapping("/api/checklist")
    public ApiResponse createChecklist(@RequestHeader HttpHeaders httpHeaders,
                                       @RequestBody SaveChecklistRequest saveChecklistRequest) {
        String authKey = HttpHeaderUtil.getAuthKey(httpHeaders);
        Long checklistId = checklistService.createChecklist(authKey, saveChecklistRequest.getTitle());
        return ApiResponse.builder()
                .contents(checklistId)
                .build();
    }

    @PutMapping("/api/checklist/{checklistId}")
    public ApiResponse updateChecklist(@RequestHeader HttpHeaders httpHeaders,
                                       @PathVariable(required = true, name = "checklistId") Long checklistId,
                                       @RequestBody SaveChecklistRequest saveChecklistRequest) {
        String authKey = HttpHeaderUtil.getAuthKey(httpHeaders);
        Long updateChecklistId = checklistService.updateChecklist(authKey, checklistId, saveChecklistRequest.getTitle());
        return ApiResponse.builder()
                .contents(updateChecklistId)
                .build();
    }

    @DeleteMapping("/api/checklist/{checklistId}")
    public ApiResponse deleteChecklist(@RequestHeader HttpHeaders httpHeaders,
                                       @PathVariable(required = true, name = "checklistId") Long checklistId) {
        String authKey = HttpHeaderUtil.getAuthKey(httpHeaders);
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
