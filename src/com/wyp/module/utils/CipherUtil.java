package com.wyp.module.utils;

import java.security.MessageDigest;
import java.util.Random;

public class CipherUtil {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/*
	 * 把inputString加密
	 */
	public static String generatePassword(String inputString) {
		return encodeByMD5(inputString);
	}

	/*
	 * 生成不同盐
	 */
	public static String randomSalt() {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		return sb.toString();
	}

	/*
	 * 加盐加密
	 */
	public static String generatePassword(String inputString, String salt) {
		return encodeByMD5(inputString + salt);
	}

	public static boolean validatePassword(String password, String inputString) {
		if (password.equals(encodeByMD5(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	public static String returnEncodeByMde(String originString) {
		return encodeByMD5(originString);
	}

	/** 对字符串进行MD5加密 */
	private static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				String pass = resultString.toUpperCase();
				return pass;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		String pwd1 = "123";
		String pwd2 = "";
		CipherUtil cipher = new CipherUtil();
		System.out.println("未加密的密码:" + pwd1);
		// 将123加密
		pwd2 = cipher.generatePassword(pwd1);
		System.out.println("加密后的密码:" + pwd2);

		System.out.print("验证密码是否下确:");
		if (cipher.validatePassword(pwd2, pwd1)) {
			System.out.println("正确");
		} else {
			System.out.println("错误");
		}
		
		String password="123456a?";
		String salt=randomSalt();
		System.out.println(salt);
		String enPwd=generatePassword(password, salt);
		System.out.println(enPwd);
	}
}
