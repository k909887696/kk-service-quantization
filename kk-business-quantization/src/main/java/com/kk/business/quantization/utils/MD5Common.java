package com.kk.business.quantization.utils;


import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
* 
* @author guojianyu Jul 5, 2017
*/

public class MD5Common {
	
    //建立加密对象的密钥和偏移量        
    private static final byte[] DESIV = { 102, 16, 93, (byte) 156, 78, 4, (byte) 218, 32 };//定义偏移量
    private static final byte[] DESKEY = { 55, 103, (byte) 246, 79, 36, 99, (byte) 167, 3 };//定义密钥
    
    
    static AlgorithmParameterSpec ivSpec = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
	private static Key key = null;

	public MD5Common() throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(DESIV, "DES");// 设置密钥参数
		ivSpec = new IvParameterSpec(DESKEY);// 设置向量
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
		key = keyFactory.generateSecret(keySpec);// 得到密钥对象
	}
	
	/**
	 * MD5加密
	 * @param strSource 需要加密的字符串
	 * @return MD5加密后的字符串
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
    public String Md5Encrypt(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	byte[] inData = source.getBytes();
    	byte[] pasByte = null;
    	try {
    		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); // 得到加密对象Cipher
    		enCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);// 设置工作模式为加密模式，给出密钥和向量
    		pasByte = enCipher.doFinal(inData);
    	} catch (Exception e){
    		e.printStackTrace();
    	}
		byte[] encodedBytes = Base64.getEncoder().encode(pasByte);
		String encodedString = new String(encodedBytes);
		return encodedString;
		
    }

    public String Md5Decrypt(String source) throws Exception {
    	Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		deCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		byte[] decodedBytes = Base64.getDecoder().decode(source);
		return new String(decodedBytes, "UTF-8");
    }
    
	/*public static void main(String[] args) throws Exception {
		MD5Common tools = new MD5Common();
		System.out.println("加密:" + tools.Md5Encrypt("efgEFG456"));
		System.out.println("解密:" + tools.Md5Decrypt("bvvhhf6UXqgTibrLt0w0Gg=="));
//		String a = Md5Util.md5("kOJcNv2vJP2oy9sz");
//		System.out.println(a);
		
	}*/
}
