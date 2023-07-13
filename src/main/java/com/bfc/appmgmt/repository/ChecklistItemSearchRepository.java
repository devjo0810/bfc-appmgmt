package com.bfc.appmgmt.repository;

import com.bfc.appmgmt.api.checklist.dto.QSearchChecklistDto;
import com.bfc.appmgmt.api.checklist.dto.QSearchChecklistItemDto;
import com.bfc.appmgmt.api.checklist.dto.SearchChecklistItemDto;
import com.bfc.appmgmt.domain.ChecklistItem;
import com.bfc.appmgmt.domain.QChecklistItem;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bfc.appmgmt.domain.QChecklistItem.*;

/**
 * packageName    : com.bfc.appmgmt.repository
 * fileName       : ChecklistItemSearchRepository
 * author         : joyousang
 * date           : 2023/07/13
 * description    :
 */
@Repository
public class ChecklistItemSearchRepository extends QuerydslRepositorySupport {
    public ChecklistItemSearchRepository() {
        super(ChecklistItem.class);
    }

    public List<SearchChecklistItemDto> search(Long checklistId) {
        return select(new QSearchChecklistItemDto(
                    checklistItem.id,
                    checklistItem.category,
                    checklistItem.productName,
                    checklistItem.brand,
                    checklistItem.needYn,
                    checklistItem.purchaseYn,
                    checklistItem.notes
                ))
                .from(checklistItem)
                .where(checklistItem.checklist.id.eq(checklistId))
                .orderBy(checklistItem.category.asc())
                .fetch();
    }

}
