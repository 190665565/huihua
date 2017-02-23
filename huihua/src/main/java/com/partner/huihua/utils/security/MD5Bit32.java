package com.partner.huihua.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;
/**
 * **********************************************
* desc: TODO 短信发送接口 秘钥加密专用（32位MD5加密并大写）
* create date: 2016年1月21日 下午2:38:26
* create author:changjiwang
* last version: 1.0
* last update date:2016年1月21日 下午2:38:26
*************************************************
 */
public class MD5Bit32 {
	private static MD5Bit32 instance;

	protected final Logger log = Logger.getLogger(getClass());

	private static Object keyObject = new Object();
	
	public static MD5Bit32 getInstance() {
		if (instance == null) {
			synchronized (keyObject) {
				if (null == instance)
					instance = new MD5Bit32();
			}
		}
		return instance;
	}
	
	
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
        'A', 'B', 'C', 'D', 'E', 'F' };  
	public static String toHexString(byte[] b) {  
	    //String to  byte  
	    StringBuilder sb = new StringBuilder(b.length * 2);    
	    for (int i = 0; i < b.length; i++) {    
	        sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);    
	        sb.append(HEX_DIGITS[b[i] & 0x0f]);    
	    }    
	    return sb.toString();    
	}  
	
	
	public String md5(String s) {  
	    try {  
	        // Create MD5 Hash  
	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");  
	        digest.update(s.getBytes());  
	        byte messageDigest[] = digest.digest();  
	                                  
	        return toHexString(messageDigest);  
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	    }  
	                          
	    return "";  
	}

}
