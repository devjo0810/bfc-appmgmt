package com.bfc.appmgmt.api.auth;

import com.bfc.appmgmt.common.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName    : com.bfc.appmgmt.api.auth
 * fileName       : LogoutApiController
 * author         : joyousang
 * date           : 2023/07/01
 * description    :
 */
@RestController
@RequiredArgsConstructor
public class LogoutApiController {
    private final SessionManager sessionManager;

    @PostMapping("/api/auth/logout")
    public ResponseEntity logout(@RequestHeader HttpHeaders httpHeaders) {
        String authKey = httpHeaders.get(HttpHeaders.AUTHORIZATION)
                .stream().findFirst().get();
        sessionManager.logout(authKey);
        return ResponseEntity.ok().build();
    }
}
