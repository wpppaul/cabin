package com.babi.common;

/**
 * 字符串暗号化类
 * @author	GuoLiangyun
 * @Date	2018.03.23
 */
public class StrEncoder {

	/*
	 * 字符串的暗号化
	 * 
	 * @param sDecrypted 明文
	 * 
	 * @param sKey 暗号化KEY
	 * 
	 * @return 暗文
	 */
	protected String getEncrypt(String sDecrypted, String sKey) {
		char[] snNum = new char[sDecrypted.length()];
		String result = "";
		// 按位异或 （异或KEY欠长时循环利用）
		for (int i = 0, j = 0; i < sDecrypted.length(); i++, j++) {
			if (j == sKey.length())
				j = 0;
			// 暗文字符 = (待暗号化字符)^(异或字符)^(1/(该字符位置+1))
			snNum[i] = (char) (sDecrypted.charAt(i) ^ sKey.charAt(j) ^ (1 / (i + 1)));
			result += (char) snNum[i];
		}
		return result;
	}

	/**
	 * 暗文的还原
	 * 
	 * @param sEncrypted
	 *            暗文
	 * @param sKey
	 *            暗号化KEY
	 * @return 明文
	 */
	protected String getDecrypt(String sEncrypted, String sKey) {
		char[] snNum = new char[sEncrypted.length()];
		String result = "";
		// 按位异或 （异或KEY欠长时循环利用）
		for (int i = 0, j = 0; i < sEncrypted.length(); i++, j++) {
			if (j == sKey.length())
				j = 0;
			// 还原字符 = (待还原字符)^(异或字符)^(1/(该字符位置+1))
			snNum[i] = (char) (sEncrypted.charAt(i) ^ sKey.charAt(j) ^ (1 / (i + 1)));
			result += snNum[i];
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(new StrEncoder().getDecrypt("123456", "babi"));
		System.out.println(new StrEncoder().getDecrypt("RSQ]WW", "babi"));
	

	}

}
