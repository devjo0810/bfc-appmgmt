package com.bfc.appmgmt.api.auth;

import com.bfc.appmgmt.dto.ApiResponse;
import com.bfc.appmgmt.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * packageName    : com.bfc.appmgmt.api.auth
 * fileName       : JoinApiController
 * author         : joyousang
 * date           : 2023/07/01
 * description    :
 */
@RestController
@RequiredArgsConstructor
public class JoinApiController {
    private final MemberService memberService;

    @PostMapping("/auth/join")
    public ApiResponse join(@RequestBody @Valid JoinRequest joinRequest) {
        Long joinId = memberService.join(joinRequest.getName(), joinRequest.getEmail(), joinRequest.getPassword());
        return ApiResponse.builder()
                .content(joinId)
                .build();
    }

    @Data
    static class JoinRequest {
        @NotEmpty(message = "이름이 비어있습니다.")
        private String name;
        @NotEmpty(message = "이메일이 비어있습니다.")
        private String email;
        @NotEmpty(message = "비밀번호가 비어있습니다.")
        private String password;
    }
}
