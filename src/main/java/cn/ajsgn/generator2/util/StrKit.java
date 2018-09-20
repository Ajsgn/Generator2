package cn.ajsgn.generator2.util;

/**
 * <p>字符串工具类</p>
 */
public class StrKit {
	
	/**
	 * <p>通过下划线（ _ ）进行驼峰式命名</p>
	 */
	public static String toCamelCase(String stringWithUnderline) {
		if (stringWithUnderline.indexOf('_') == -1)
			return stringWithUnderline;
		
		stringWithUnderline = stringWithUnderline.toLowerCase();
		char[] fromArray = stringWithUnderline.toCharArray();
		char[] toArray = new char[fromArray.length];
		int j = 0;
		for (int i=0; i<fromArray.length; i++) {
			if (fromArray[i] == '_') {
				// 当前字符为下划线时，将指针后移一位，将紧随下划线后面一个字符转成大写并存放
				i++;
				if (i < fromArray.length)
					toArray[j++] = Character.toUpperCase(fromArray[i]);
			}
			else {
				toArray[j++] = fromArray[i];
			}
		}
		return new String(toArray, 0, j);
	}
	
	/**
	 * <p>首字母变小写</p>
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	
	/**
	 * <p>首字母变大写</p>
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	
}
