package com.partner.huihua.utils.common;

import java.util.UUID;

public class ValidateUtil {
	
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z" };

	
	public static String[] chars_num = new String[] { "0", "1", "2", "3", "4", "5",
	      "6", "7", "8", "9" };

	  public static String getNumValidate() {
	    StringBuffer shortBuffer = new StringBuffer();
	    String uuid = UUID.randomUUID().toString().replace("-", "");
	    for (int i = 0; i < 8; i++) {
	      String str = uuid.substring(i * 4, i * 4 + 4);
	      int x = Integer.parseInt(str, 16);
	      shortBuffer.append(chars_num[x % 0x0a]);
	    }
	    return shortBuffer.toString();

	  }

	public static String getValidate8th() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
		    String str = uuid.substring(i * 4, i * 4 + 4);
		    int x = Integer.parseInt(str, 16);
		    shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getNumValidate());
	}

}
