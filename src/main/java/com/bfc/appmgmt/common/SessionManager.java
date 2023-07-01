package com.bfc.appmgmt.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * packageName    : com.bfc.appmgmt.common
 * fileName       : LoginSessionManager
 * author         : joyousang
 * date           : 2023/07/01
 * description    :
 */
@Component
public class SessionManager {
    private Map<String, Long> sessionMap = new HashMap<>();

    public boolean isLogin(String key) {
        Long memberId = sessionMap.get(key);
        return memberId == null ? false : true;
    }

    public void logout(String key) {
        sessionMap.remove(key);
    }

    public String login(Long memberId) {
        List<String> loginSessions = sessionMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(memberId))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        for (String sessionKey : loginSessions) {
            sessionMap.remove(sessionKey);
        }
        String key = UUID.randomUUID().toString();
        sessionMap.put(key, memberId);
        return key;
    }
}
