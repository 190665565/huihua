package com.partner.huihua.utils.common;

import java.util.regex.Pattern;

public class StringUtil {

	private static Pattern regidPattern = Pattern.compile("[\\w-]*");

	public static boolean isDigital(String str) {
		Pattern p = Pattern.compile("(0|([1-9][0-9]*))(\\.[0-9]+)?");
		return p.matcher(str).matches();
	}

	public static boolean isChinese(String scr) {
		return !regidPattern.matcher(scr).matches();
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isAllChinese(String s) {
		if (null == s || "".equals(s.trim()))
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (!isChinese(s.charAt(i)))
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isContainsChinese(String s) {
		if (null == s || "".equals(s.trim()))
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (isChinese(s.charAt(i)))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @param a
	 *            char
	 * @return boolean
	 */
	public static boolean isChinese(char a) {
		int v = (int) a;
		return (v >= 19968 && v <= 171941) || v == 183;
	}

	/**
	 * 
	 * @param input
	 * @return String
	 */
	public static final String escapeHTMLTag(String input) {
		if (input == null) {
			return "";
		}
		input = input.trim().replaceAll("&", "&amp;");
		input = input.trim().replaceAll("<", "&lt;");
		input = input.trim().replaceAll(">", "&gt;");
		input = input.trim().replaceAll("\t", "    ");
		input = input.trim().replaceAll("\r\n", "\n");
		input = input.trim().replaceAll("\n", "<br>");
		input = input.trim().replaceAll("  ", " &nbsp;");
		input = input.trim().replaceAll("'", "&#39;");
		input = input.trim().replaceAll("\\\\", "&#92;");
		return input;
	}

	public static String cleanHtmlTag(String htmlText) {
		String reg = "</?[a-z][a-z0-9]*[^<>]*>?";
		return htmlText.replaceAll(reg, "");
	}

	/**
	 *
	 *
	 *
	 * @param str
	 * @return
	 */
	public static String null2Trim(String str) {
		return str == null ? "" : str.trim();
	}

	public static String replaceXmlEntity(String xml) {
		xml = xml.replaceAll("&amp;", "&");
		xml = xml.replaceAll("&quot;", "\"");
		xml = xml.replaceAll("&gt;", ">");
		xml = xml.replaceAll("&nbsp;", " ");
		xml = xml.replaceAll("&apos;", "'");
		return xml;
	}

	/**
	 * 
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return !notEmpty(str);
	}

	/**
	 * 
	 * 
	 * @param Str
	 * @return
	 */
	public static boolean notEmpty(String str) {
		if (str != null && str.trim().length() > 0)
			return true;
		return false;
	}

	public static void main(String[] argv) {
		boolean result = StringUtil.isDigital("0.1");

		// int length = StringUtil.getStringLength("呵呵a");

		int length = "呵呵".length();

		System.out.println(result);

		System.out.println(length);
	}
}
