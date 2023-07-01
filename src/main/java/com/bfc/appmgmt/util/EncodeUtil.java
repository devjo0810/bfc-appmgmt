package com.bfc.appmgmt.util;

import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * packageName    : com.bfc.appmgmt.util
 * fileName       : EncodeUtil
 * author         : joyousang
 * date           : 2023/07/01
 * description    :
 */
@UtilityClass
public class EncodeUtil {

    private static final String ENCODE_ALGORITHM = "SHA-256";

    public static String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            // 알고리즘이 지원되지 않는 경우 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        String hashedPassword = encodePassword(rawPassword);
        return hashedPassword != null && hashedPassword.equals(encodedPassword);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
