package com.bfc.appmgmt.interceptor;

import com.bfc.appmgmt.common.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private final SessionManager sessionManager;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authKey = request.getHeader(HttpHeaders.AUTHORIZATION);

        // api 요청시 header AUTHORIZATION 값 체크
        if (!StringUtils.hasText(authKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        // session manager 에 authKey 가 존재하는지 체크
        if (!sessionManager.isLogin(authKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        return true;
    }

}
