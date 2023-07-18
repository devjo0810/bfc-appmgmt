package com.bfc.appmgmt.domain;

import com.bfc.appmgmt.api.checklist.dto.SaveChecklistItemDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * packageName    : com.bfc.appmgmt.domain
 * fileName       : ChecklistItem
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name = "CHECKLIST_ITEM_SEQ_GENERATOR"
        , sequenceName = "CHECKLIST_ITEM_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
public class ChecklistItem extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKLIST_ITEM_SEQ_GENERATOR")
    @Column(name = "checklist_item_id")
    private Long id;
    private String category;
    private String productName;
    private String brand;
    private String needYn;
    private String purchaseYn;
    private String notes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checklist_id")
    private Checklist checklist;

    @Builder
    public ChecklistItem(String category, String productName, String brand, String needYn, String purchaseYn, String notes, Checklist checklist) {
        this.category = category;
        this.productName = productName;
        this.brand = brand;
        this.needYn = needYn;
        this.purchaseYn = purchaseYn;
        this.notes = notes;
        this.checklist = checklist;
    }

    public void update(SaveChecklistItemDto item) {
        this.category = item.getCategory();
        this.productName = item.getProductName();
        this.brand = item.getBrand();
        this.needYn = item.getNeedYn();
        this.purchaseYn = item.getPurchaseYn();
        this.notes = item.getNotes();
    }
}
