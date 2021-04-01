package com.example.graphql.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

public class CryptoUtil {
    /** 비밀번호 찾기 */
    public static class Password {
        public static String encrypt(String plain) {
            // strength : 사용할 로그 반올림 자릿 수
            BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(11);
            String encodePwd = bcryptPasswordEncoder.encode(plain);
            return encodePwd;
        }

        public static boolean match(String plain, String encodePwd) {
            BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(11);
            boolean result = bcryptPasswordEncoder.matches(plain, encodePwd);
            return result;
        }
    }
}
