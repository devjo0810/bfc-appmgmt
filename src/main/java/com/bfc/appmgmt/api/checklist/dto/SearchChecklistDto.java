package com.bfc.appmgmt.api.checklist.dto;

import com.bfc.appmgmt.util.DateUtil;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.bfc.appmgmt.dto
 * fileName       : SearchChecklistDto
 * author         : joyousang
 * date           : 2023/07/12
 * description    :
 */
@Data
@NoArgsConstructor
public class SearchChecklistDto {
    private Long checklistId;
    private String title;
    private String createdAt;
    private String updatedAt;
    private Long itemsCount;

    @QueryProjection
    public SearchChecklistDto(Long checklistId, String title, LocalDateTime createdAt, LocalDateTime updatedAt, Long itemsCount) {
        this.checklistId = checklistId;
        this.title = title;
        this.createdAt = DateUtil.dateToString(createdAt);
        this.updatedAt = DateUtil.dateToString(updatedAt);
        this.itemsCount = itemsCount;
    }
}
