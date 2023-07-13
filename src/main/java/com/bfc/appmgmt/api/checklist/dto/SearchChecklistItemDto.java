package com.bfc.appmgmt.api.checklist.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * packageName    : com.bfc.appmgmt.api.checklist.dto
 * fileName       : SearchChecklistItemDto
 * author         : joyousang
 * date           : 2023/07/13
 * description    :
 */
@Data
public class SearchChecklistItemDto {
    private Long checklistItemId;
    private String category;
    private String productName;
    private String brand;
    private String needYn;
    private String purchaseYn;
    private String notes;

    @QueryProjection
    public SearchChecklistItemDto(Long checklistItemId, String category, String productName, String brand, String needYn, String purchaseYn, String notes) {
        this.checklistItemId = checklistItemId;
        this.category = category;
        this.productName = productName;
        this.brand = brand;
        this.needYn = needYn;
        this.purchaseYn = purchaseYn;
        this.notes = notes;
    }
}
