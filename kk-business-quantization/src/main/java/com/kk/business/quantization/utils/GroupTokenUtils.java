package com.kk.business.quantization.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author morgan
 * @since 2025-12-11
 */
public class GroupTokenUtils {


    /**
     * 集团内部调用-生成日志唯一码，以及生成Token请求头；根据密钥；
     *
     * @param secretKey 配置键
     * @return 包含时间戳和token的请求头映射
     */
    public static Map<String, String> groupTokenHeader(String secretKey) {
        if (StringUtils.isEmpty(secretKey)) {
            return null;
        }
        Map<String, String> headers = new HashMap<>();
        long timestamp = System.currentTimeMillis();
        String token = groupTokenGet(secretKey, timestamp);
        headers.put("WebApiBaseGroupApiTimestamp", String.valueOf(timestamp));
        headers.put("WebApiBaseGroupApiToken", token);
        return headers;
    }

    /**
     * 集团内部调用-根据时间戳和密钥生成Token，HMACSHA256签名
     *
     * @param secretKey 密钥
     * @param timestamp 时间戳（毫秒），使用方法：long timestamp = BaseSystemHelper.getCurrentTimeMillis();
     * @return Token
     */
    public static String groupTokenGet(String secretKey, long timestamp) {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new RuntimeException("密钥不能为空");
        }

        // 生成token: 使用密钥和时间戳生成HMACSHA256哈希
        String dataToHash = secretKey + "&" + timestamp;
        return hmacSHA256(dataToHash, secretKey);
    }

    /**
     * HmacSHA256
     * @param message 需要加密的值
     * @param secret 加密密钥
     * @return
     */
    private static String hmacSHA256(String message, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(message.getBytes("UTF-8"));
            return Base64.encodeBase64String(signData);
        } catch (Exception e) {
            throw new RuntimeException(String.format("hmacSHA256加密异常，原因：%s", e.getMessage()));
        }
    }

}
