package com.dolmengi.common.constants;

import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public final class Constants {

    private Constants() {}

    // Token
    public static final SecretKey SECRET_KEY = new SecretKeySpec(Base64.getDecoder().decode("VlwEyVBsYt9V7zq57TejMnVUyzblYcfPQye08f7MGVA9XkHa"), "HmacSHA256");
    public static final long EXPIRE_MILLIS = 86400000;    // 1분: 60000, 30분: 1800000, 1시간: 3600000, 3시간: 10800000, 6시간: 21600000, 12시간: 43200000, 1일: 86400000

    // AES
    public static final SecretKey AES_KEY = new SecretKeySpec(Base64.getDecoder().decode("qrcSzDvEPe24n6LYvUY2AZIamUEMC4bgzPtVLGm8h38="), "AES");
    public static final String AES_ALGORITHM = "AES/GCM/NoPadding";
    public static final int AES_IV_LENGTH = 12;
    public static final int AES_TAG_LENGTH = 128;

}
