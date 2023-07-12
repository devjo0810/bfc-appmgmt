package com.bfc.appmgmt.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public SearchChecklistDto(Long checklistId, String title, String createdAt, String updatedAt, Long itemsCount) {
        this.checklistId = checklistId;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.itemsCount = itemsCount;
    }
}
