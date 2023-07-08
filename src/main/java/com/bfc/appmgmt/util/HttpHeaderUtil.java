package com.bfc.appmgmt.util;

import com.bfc.appmgmt.exception.UnauthorizedException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.bfc.appmgmt.util
 * fileName       : HttpHeaderUtil
 * author         : joyousang
 * date           : 2023/07/08
 * description    :
 */
@UtilityClass
public class HttpHeaderUtil {

    public static String getAuthKey(HttpHeaders httpHeaders) {
        List<String> authKeys = Optional.ofNullable(httpHeaders.get(HttpHeaders.AUTHORIZATION))
                .orElseThrow(() -> new UnauthorizedException("잘못된 접근 방식입니다."));
        return authKeys.stream().findFirst().get();
    }
}
