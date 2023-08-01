package com.bfc.appmgmt.config.batch;

import com.bfc.appmgmt.service.DbHealthyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.bfc.appmgmt.config.batch
 * fileName       : DbHealthyBatch
 * author         : joyousang
 * date           : 2023/08/01
 * description    :
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DbHealthyBatch {

    private final DbHealthyService dbHealthyService;

    public void healthyCheck() {
        log.info("db healthy check before");
        dbHealthyService.deleteAll();
        dbHealthyService.healthyCheck();
        log.info("db healthy check after");
    }
}
