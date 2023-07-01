package com.bfc.appmgmt.api.auth;

import com.bfc.appmgmt.dto.ApiResponse;
import com.bfc.appmgmt.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * packageName    : com.bfc.appmgmt.api.auth
 * fileName       : LoginApiController
 * author         : joyousang
 * date           : 2023/07/01
 * description    :
 */
@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final MemberService memberService;

    @PostMapping("/api/auth/v1/login")
    public ApiResponse loginV1(@RequestBody LoginRequest loginRequest) {
        String authKey = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ApiResponse.builder().content(authKey).build();
    }

    @Data
    public static class LoginRequest {
        @NotEmpty(message = "이메일이 비어있습니다.")
        private String email;
        @NotEmpty(message = "비밀번호가 비어있습니다.")
        private String password;
    }
}
