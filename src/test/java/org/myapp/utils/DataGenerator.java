package org.myapp.utils;

import org.myapp.models.User;

import java.util.Random;

public class DataGenerator {

    private static String generateUserName() {
        return randomString(8);
    }

    private static String generatePassword() {
        return randomString(12);
    }

    public static User realUser() {
        return User.builder()
                .name(generateUserName()).password(generatePassword())
                .build();
    }

    private static String randomString(int length) {
        String letters = "qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        return sb.toString();
    }

}
