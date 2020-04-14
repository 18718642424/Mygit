package com.qf.auction.util;

import java.util.Random;

public class ValidataCodeUtil {
	// 工具类的特点就是 所有的函数都是公开的并且是静态的
	public static String getValidataCode() {
		String sysCode = "0123456789qwertyuioplkjhgfdsazxcvbnm";
		StringBuilder stringBuilder = new StringBuilder(4);
		for (int i = 0; i < 4; i++) { 
			// charAt 根据字符串所在的位置返回对应的字符
			// nextInt 返回的是 0-35
			char temp = sysCode.charAt(new Random().nextInt(sysCode.length()));
			stringBuilder.append(temp);
		}
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getValidataCode());
	}

}
