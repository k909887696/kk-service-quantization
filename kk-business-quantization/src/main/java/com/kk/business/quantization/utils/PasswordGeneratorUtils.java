package com.kk.business.quantization.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description:
 * auth: kk
 * date: 2025-11-10
 */
public class PasswordGeneratorUtils {

    // 定义字符集
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "1@#$_*%/.+::=";

    private static final String ALL_LETTERS = UPPERCASE_LETTERS + LOWERCASE_LETTERS;
    private static final String ALL_CHARS = ALL_LETTERS + DIGITS + SPECIAL_CHARS;

    private static final SecureRandom random = new SecureRandom();

    /**
     * 生成随机密码
     * @param length 密码长度，必须在8-20之间
     * @return 生成的密码字符串
     * @throws IllegalArgumentException 如果长度不在有效范围内
     */
    public static String generatePassword(int length) {
        if (length < 8 || length > 20) {
            throw new IllegalArgumentException("密码长度必须在8-20位之间");
        }

        StringBuilder password = new StringBuilder();
        List<Character> charList = new ArrayList<>();

        // 确保至少包含两种类型的字符
        // 先从每种类型中随机选择一个字符
        charList.add(ALL_LETTERS.charAt(random.nextInt(ALL_LETTERS.length()))); // 字母
        charList.add(DIGITS.charAt(random.nextInt(DIGITS.length()))); // 数字

        // 如果只需要两种类型，则全部用完；否则继续添加随机字符
        for (int i = 2; i < length; i++) {
            charList.add(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        // 打乱字符顺序
        Collections.shuffle(charList, random);

        // 构建最终密码
        for (char c : charList) {
            password.append(c);
        }

        return password.toString();
    }

    /**
     * 生成指定长度范围内的随机密码
     * @param minLength 最小长度(8-20)
     * @param maxLength 最大长度(8-20)
     * @return 生成的密码字符串
     */
    public static String generatePassword(int minLength, int maxLength) {
        if (minLength < 8 || maxLength > 20 || minLength > maxLength) {
            throw new IllegalArgumentException("密码长度参数无效，必须在8-20范围内且最小长度不能大于最大长度");
        }

        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        return generatePassword(length);
    }

    /**
     * 生成默认长度(8-12位)的随机密码
     * @return 生成的密码字符串
     */
    public static String generatePassword() {
        return generatePassword(8, 12);
    }
}
