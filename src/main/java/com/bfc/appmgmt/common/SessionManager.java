package com.bfc.appmgmt.common;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SessionManager {
    private Map<String, Long> sessionMap = new HashMap<>();

    public boolean isLogin(String key) {
        Long memberId = sessionMap.get(key);
        log.info("### isLogin member id : {}", memberId);
        return memberId == null ? false : true;
    }

    public void logout(String key) {
        log.info("### logout member id : {}", sessionMap.get(key));
        sessionMap.remove(key);
    }

    public String login(Long memberId) {
        List<String> loginSessions = sessionMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(memberId))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        log.info("### login before key list... memberId : {}, keys : {}", memberId, loginSessions);
        loginSessions.forEach(sessionKey -> sessionMap.remove(sessionKey));

        String key = UUID.randomUUID().toString();
        sessionMap.put(key, memberId);
        log.info("### login after key memberId : {}, key : {}", memberId, key);
        return key;
    }

    public Long getMemberId(String authKey) {
        return sessionMap.get(authKey);
    }
}
