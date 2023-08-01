package com.bfc.appmgmt.config;

import com.bfc.appmgmt.config.batch.DbHealthyBatch;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * packageName    : com.bfc.appmgmt.config
 * fileName       : BatchScheduleConfig
 * author         : joyousang
 * date           : 2023/08/01
 * description    :
 */
@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class BatchScheduleConfig {

    private final DbHealthyBatch dbHealthyBatch;

    @Scheduled(cron = "${batch.db-healthy.cron}")
    public void dbHealthyBatchRun() {
        dbHealthyBatch.healthyCheck();
    }
}
