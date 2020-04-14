package com.qf.auction.util;

public class StringUtil {

	public static boolean isEmpty(String arg) {
		return arg == null || arg.equals("");
	}

	public static boolean notEmpty(Object arg) {
		return arg != null && !arg.equals("");
	}
}
