package com.bfc.appmgmt.service;

import com.bfc.appmgmt.domain.DbHealthy;
import com.bfc.appmgmt.repository.DbHealthyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.bfc.appmgmt.service
 * fileName       : DbHealthyService
 * author         : joyousang
 * date           : 2023/08/01
 * description    :
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DbHealthyService {

    private final DbHealthyRepository dbHealthyRepository;

    @Transactional
    public void deleteAll() {
        dbHealthyRepository.deleteAllInBatch();
    }

    @Transactional
    public void healthyCheck() {
        dbHealthyRepository.save(new DbHealthy());
    }

    public DbHealthy findLastCheck() {
        return dbHealthyRepository.findAll().stream().findFirst().get();
    }
}
