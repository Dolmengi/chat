package com.dolmengi.api.commons.util;

import static com.dolmengi.common.constants.Constants.AES_ALGORITHM;
import static com.dolmengi.common.constants.Constants.AES_IV_LENGTH;
import static com.dolmengi.common.constants.Constants.AES_KEY;
import static com.dolmengi.common.constants.Constants.AES_TAG_LENGTH;
import static com.dolmengi.common.constants.Constants.SECRET_KEY;

import com.dolmengi.common.constants.Constants;
import com.dolmengi.common.constants.UserRole;
import com.dolmengi.common.domain.account.Account;
import com.dolmengi.common.domain.security.SessionContext;
import com.dolmengi.common.util.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

@Slf4j
public final class JwtUtils {

    private JwtUtils() {}

    /**
     * Token 생성
     */
    public static String createToken(Account account, UserRole role) {
        return createToken(account, role, null);
    }

    /**
     * Token 생성
     */
    public static String createToken(Account account, UserRole role, Long expireMillis) {
        if (ObjectUtils.isEmpty(expireMillis) || expireMillis == 0) {
            expireMillis = Constants.EXPIRE_MILLIS;
        }

        SessionContext sessionContext = new SessionContext(account, role);

        Instant now = Instant.now();
        Instant expireDate = now.plus(expireMillis, ChronoUnit.MILLIS);

        return Jwts.builder()
                .subject(encrypt(sessionContext))
                .issuedAt(DateUtils.toDate(now))
                .expiration(DateUtils.toDate(expireDate))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Token 검증
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid Token.", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired Token.", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported Token.", e);
        } catch (IllegalArgumentException e) {
            log.error("Claims string is empty.", e);
        }

        return false;
    }

    /**
     * SessionContext 추출
     */
    public static SessionContext getSessionContext(String token) {
        String subject = parseClaims(token).getSubject();

        return decrypt(subject);
    }

    /**
     * Claims 추출
     */
    private static Claims parseClaims(String token) {
        try {
            return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    /**
     * SessionContext 암호화
     */
    private static String encrypt(SessionContext sessionContext) {
        try {
            String plainText = JsonUtils.toJson(sessionContext);
            byte[] iv = new byte[AES_IV_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            GCMParameterSpec parameterSpec = new GCMParameterSpec(AES_TAG_LENGTH, iv);
            cipher.init(Cipher.ENCRYPT_MODE, AES_KEY, parameterSpec);

            byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
            byteBuffer.put(iv);
            byteBuffer.put(cipherText);

            return Base64.getEncoder().encodeToString(byteBuffer.array());
        } catch (Exception e) {
            throw new RuntimeException("AES encrypt failed.", e);
        }
    }

    /**
     * SessionContext 복호화
     */
    private static SessionContext decrypt(String encrypt) {
        try {
            byte[] decoded = Base64.getDecoder().decode(encrypt);
            ByteBuffer buffer = ByteBuffer.wrap(decoded);

            byte[] iv = new byte[AES_IV_LENGTH];
            buffer.get(iv);

            byte[] cipherBytes = new byte[buffer.remaining()];
            buffer.get(cipherBytes);

            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            GCMParameterSpec parameterSpec = new GCMParameterSpec(AES_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, AES_KEY, parameterSpec);

            byte[] plainText = cipher.doFinal(cipherBytes);
            String jsonString = new String(plainText, StandardCharsets.UTF_8);

            return JsonUtils.fromJson(jsonString, SessionContext.class);
        } catch (Exception e) {
            throw new RuntimeException("AES decrypt failed.", e);
        }
    }

}
