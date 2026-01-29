package com.dolmengi.common.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GeneratorUtils {

    private GeneratorUtils() {}

    private static final String TEXTS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private static final String NUMBERS = "123456789";
    private static final String SPECIALS = "!@#$%^*?";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String randomText(int length) {
        return getRandomChars(TEXTS, length);
    }

    public static String randomNumber(int length) {
        return getRandomChars(NUMBERS, length);
    }

    public static String randomSpecial(int length) {
        return getRandomChars(SPECIALS, length);
    }

    public static String randomPassword() {
        String randomText = randomText(6);
        String randomNumber = randomNumber(1);
        String randomSpecial = randomSpecial(1);

        return shuffleAll(randomText, randomNumber, randomSpecial);
    }

    private static String getRandomChars(String source, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(source.charAt(RANDOM.nextInt(source.length())));
        }

        return sb.toString();
    }

    private static String shuffleAll(String... parts) {
        List<Character> chars = new ArrayList<>();
        for (String part : parts) {
            for (char c : part.toCharArray()) {
                chars.add(c);
            }
        }

        Collections.shuffle(chars, RANDOM);
        StringBuilder sb = new StringBuilder(chars.size());
        for (char c : chars) {
            sb.append(c);
        }

        return sb.toString();
    }

}
