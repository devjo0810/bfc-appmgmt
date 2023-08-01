package com.bfc.appmgmt.api;

import com.bfc.appmgmt.domain.DbHealthy;
import com.bfc.appmgmt.service.DbHealthyService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * packageName    : com.bfc.appmgmt.api
 * fileName       : HealthyApiController
 * author         : joyousang
 * date           : 2023/08/01
 * description    :
 */
@RestController
@RequiredArgsConstructor
public class HealthyApiController {

    private final DbHealthyService dbHealthyService;

    @GetMapping("/healthy")
    public HealthyResponse healthyCheck() {
        DbHealthy lastCheck = dbHealthyService.findLastCheck();
        return HealthyResponse.builder()
                .status("UP")
                .dbCheckedAt(lastCheck.getCheckedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    @Data
    @Builder
    static class HealthyResponse {
        String status;
        String dbCheckedAt;
    }
}
