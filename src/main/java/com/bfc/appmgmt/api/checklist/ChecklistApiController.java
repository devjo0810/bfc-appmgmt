package com.bfc.appmgmt.api.checklist;

import com.bfc.appmgmt.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/api/v1/checklist")
    public ApiResponse getChecklistV1() {
        return ApiResponse.builder()
                .contents(List.of("item1", "item2", "item3"))
                .build();
    }
}
