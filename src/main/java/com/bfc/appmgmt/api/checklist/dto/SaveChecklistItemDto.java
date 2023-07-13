package com.bfc.appmgmt.api.checklist.dto;

import com.bfc.appmgmt.domain.Checklist;
import com.bfc.appmgmt.domain.ChecklistItem;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * packageName    : com.bfc.appmgmt.api.checklist.dto
 * fileName       : SaveChecklistItemDto
 * author         : joyousang
 * date           : 2023/07/13
 * description    :
 */
@Data
public class SaveChecklistItemDto {
    private Long checklistItemId;
    private String category;
    @NotEmpty(message = "상품명이 비어있습니다.")
    private String productName;
    private String brand;
    @NotEmpty(message = "필요여부값이 비어있습니다.")
    private String needYn;
    @NotEmpty(message = "구매여부값이 비어있습니다.")
    private String purchaseYn;
    private String notes;

    public ChecklistItem toEntity(Checklist checklist) {
        return ChecklistItem.builder()
                .category(category)
                .productName(productName)
                .brand(brand)
                .needYn(needYn)
                .purchaseYn(purchaseYn)
                .notes(notes)
                .checklist(checklist)
                .build();
    }

    public boolean isCreate() {
        return this.checklistItemId == null;
    }

    public boolean isUpdate() {
        return this.checklistItemId != null;
    }
}

