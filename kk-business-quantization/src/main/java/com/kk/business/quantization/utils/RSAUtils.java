package com.kk.business.quantization.utils;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";


    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;



    /**
     * 私钥解密
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }


    /** */
    /**
     * 公钥加密
     * @param data 源数据
     * @param publicKey 私钥(BASE64编码)
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 公钥加密
     */
    public static String encrypted(String data, String publicKey) {
        try {
            data = Base64.encodeBase64String(encryptByPublicKey(data.getBytes(), publicKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 私钥解密
     */
    public static String decrypt(String data, String privateKey) {
        String temp = "";
        try {
            byte[] rs = Base64.decodeBase64(data);
            temp = new String(RSAUtils.decryptByPrivateKey(rs, privateKey),"UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static void main(String args[]){
        /**
         * {
         *  "customer": {
         *      "web_token": "abc"
         *        }
         * }
         * 正确的json格式即可。注意这只是示例，最终加密的json串还需要再确认
         */
        //{"customer":{"c_cf_公司简称":"上海威士伯企业管理C110284","c_name":"张建中P251515","c_phone":"18626259566"}}
        //String text ="{\n" + "\t\"customer\": {\n" + "\t\t\"web_token\": \"abc\"\n" + "       }\n" + "}  ";
        //String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCz/jNc/zKg1epts7CcnVbdSUMQAiNjgbqt3q4qBMWWk6j5smjhrzjcukPhgZgHiwEgDcJxTlu+nzgm4rTn0Su9AWuMTHF2GEVoT5Dn9SZ1AFlicCSGt0Si2i+MyN0QnHPGsWGiC7ZbS52knfqt4OZfV/a4qdxjfMdDzeUfJz4MewIDAQAB";
        
        String text ="{\"customer\":{\"c_cf_公司简称\":\"上海威士伯企业管理C110284\",\"c_name\":\"张建中P251515\",\"c_phone\":\"18626259566\"}}";
        String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRqCQ6MV6dEJzIE98xU9373yQH/CJB7KDlTsk/c82/DzDWXLXi9PfDNbnde2MfjbO8A25bHMQPQkQRTFfAmFWyxZn+ObVYhHcbRCcVnyYf502bn1H0fKjytYnCNnzEqhimHdQgZLrfo2L86+tTsqbDc/i1zIkCKBMXzlRNmRbwEwIDAQAB";
        String encryptedStr = RSAUtils.encrypted(text,publicKey);// 这个就是后面customer_encrypt参数
        System.out.println("密文:"+encryptedStr);
    }
}