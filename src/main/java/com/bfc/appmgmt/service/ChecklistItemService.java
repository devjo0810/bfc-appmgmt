package com.bfc.appmgmt.service;

import com.bfc.appmgmt.repository.ChecklistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.bfc.appmgmt.service
 * fileName       : ChecklistItemService
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
@Service
@RequiredArgsConstructor
public class ChecklistItemService {
    private final ChecklistItemRepository checklistItemRepository;
}
