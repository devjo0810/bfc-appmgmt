package com.bfc.appmgmt.api.auth;

import com.bfc.appmgmt.domain.Member;
import com.bfc.appmgmt.dto.ApiResponse;
import com.bfc.appmgmt.exception.UnauthorizedException;
import com.bfc.appmgmt.service.MemberService;
import com.bfc.appmgmt.util.HttpHeaderUtil;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.bfc.appmgmt.api.auth
 * fileName       : MeApiController
 * author         : joyousang
 * date           : 2023/07/08
 * description    :
 */
@RestController
@RequiredArgsConstructor
public class MeApiController {
    private final MemberService memberService;
    @GetMapping("/auth/me")
    public ApiResponse me(@RequestHeader HttpHeaders httpHeaders) {
        String authKey = HttpHeaderUtil.getAuthKey(httpHeaders);
        Member findMember = memberService.findByAuthKey(authKey);
        return ApiResponse.builder().contents(findMember.toMeResponse()).build();
    }

    @Data
    @Builder
    public static class MeResponse {
        private Long memberId;
        private String name;
        private String email;
    }
}
