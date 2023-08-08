package com.bfc.appmgmt.repository;

import com.bfc.appmgmt.api.checklist.dto.QSearchChecklistDto;
import com.bfc.appmgmt.domain.Checklist;
import com.bfc.appmgmt.api.checklist.dto.SearchChecklistDto;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bfc.appmgmt.domain.QChecklist.*;
import static com.bfc.appmgmt.domain.QChecklistItem.*;

/**
 * packageName    : com.bfc.appmgmt.repository
 * fileName       : ChecklistSearchRepository
 * author         : joyousang
 * date           : 2023/07/12
 * description    :
 */
@Repository
public class ChecklistSearchRepository extends QuerydslRepositorySupport {
    public ChecklistSearchRepository() {
        super(Checklist.class);
    }

    public List<SearchChecklistDto> searchWithItemsCount(Long memberId) {
        return select(new QSearchChecklistDto(
                checklist.id,
                checklist.title,
                checklist.createdAt,
                checklist.updatedAt,
                JPAExpressions
                        .select(checklistItem.count())
                        .from(checklistItem)
                        .where(checklistItem.checklist.id.eq(checklist.id))))
                .from(checklist)
                .where(
                        checklist.member.id.eq(memberId)
                )
                .fetch();
    }

}
