package com.bfc.appmgmt.service;

import com.bfc.appmgmt.domain.Checklist;
import com.bfc.appmgmt.repository.CheckListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.bfc.appmgmt.service
 * fileName       : ChecklistServiceTest
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
@SpringBootTest
@Transactional
class ChecklistServiceTest {

    @Autowired ChecklistService checklistService;
    @Autowired
    CheckListRepository checkListRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 체크리스트생성(){
        //given
        String title = "체크리스트1";

        //when
        Long checklist = checklistService.createChecklist(title);

        em.flush();
        em.clear();

        //then
        Checklist findChecklist = checkListRepository.findByTitle(title).get(0);
        assertEquals(title, findChecklist.getTitle());
    }
}