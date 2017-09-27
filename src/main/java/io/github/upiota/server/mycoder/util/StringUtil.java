package io.github.upiota.server.mycoder.util;

public class StringUtil {
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}

	public static String captureCharAt(String str, int index) {
		char[] cs = str.toCharArray();
		
		int len = cs.length;
		if(len<=index){
			return str;
		}
		
		char c = cs[index];

		if (97 <= c && c <= 122) {
			c ^= 32;
			cs[index] = c;
		}

		return String.valueOf(cs);

	}

	public static String _toA(String str) {
		int i = str.indexOf("_");
		if (i != -1) {
			str = str.replaceFirst("_", "");
			str = StringUtil.captureCharAt(str, i);
			str = StringUtil._toA(str);
		}

		return str;
	}

	public static void main(String[] args) {
		String str = "hello_world_how_are_you";

		System.out.println(_toA(str));
		System.out.println(StringUtil.captureCharAt("", 0));
	}

}
