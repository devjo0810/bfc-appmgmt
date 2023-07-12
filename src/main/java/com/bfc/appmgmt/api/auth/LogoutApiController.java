package com.bfc.appmgmt.api.auth;

import com.bfc.appmgmt.common.SessionManager;
import com.bfc.appmgmt.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/auth/logout")
    public ResponseEntity logout(@RequestHeader HttpHeaders httpHeaders) {
        List<String> authKeys = Optional.ofNullable(httpHeaders.get(HttpHeaders.AUTHORIZATION))
                .orElseThrow(() -> new UnauthorizedException("잘못된 접근 방식입니다."));
        String authKey = authKeys.stream().findFirst().get();
        sessionManager.logout(authKey);
        return ResponseEntity.ok().build();
    }
}
