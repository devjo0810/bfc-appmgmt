package com.bfc.appmgmt.interceptor;

import com.bfc.appmgmt.common.SessionManager;
import com.bfc.appmgmt.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * packageName    : com.bfc.appmgmt.interceptor
 * fileName       : AuthInterceptor
 * author         : joyousang
 * date           : 2023/07/02
 * description    :
 */
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final List<String> PASS_HTTP_METHODS = List.of("OPTIONS");
    private final SessionManager sessionManager;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String httpMethod = request.getMethod();
        // auth 인증이 필요없는 http method 요청일 경우 패스
        if (PASS_HTTP_METHODS.contains(httpMethod)) {
            return true;
        }

        String authKey = request.getHeader(HttpHeaders.AUTHORIZATION);

        // api 요청시 header AUTHORIZATION 값 체크
        if (!StringUtils.hasText(authKey)) {
            throw new UnauthorizedException("잘못된 접근방식입니다.");
        }

        // session manager 에 authKey 가 존재하는지 체크
        if (!sessionManager.isLogin(authKey)) {
            throw new UnauthorizedException("잘못된 접근방식입니다.");
        }

        return true;
    }

}
