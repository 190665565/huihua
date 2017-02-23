package com.partner.huihua.utils.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * 获得主机信息
 * @author
 *
 */
public class IPAddressUtil {

	public static String getHostInfo(InetAddress IP) {
		String name = IP.getHostName();
		String address = IP.getHostAddress();
		return "Host Name: " + name + ", Address: " + address;
	}

	public static String getLocalHostInfo() {
		InetAddress IP;
		try {
			IP = InetAddress.getLocalHost();
			return getHostInfo(IP);
		} catch (UnknownHostException e) {
			return "Get local host exception";
		}
	}

	public static String getByNameInfo(String hostName) {
		InetAddress IP;
		try {
			IP = InetAddress.getByName(hostName);
			return getHostInfo(IP);
		} catch (UnknownHostException e) {
			return "Get IP by name exception";
		}
	}

	public static String getByAddressInfo(byte[] bs) {
		InetAddress IP;
		try {
			IP = InetAddress.getByAddress(bs);
			return getHostInfo(IP);
		} catch (UnknownHostException e) {
			return "Get IP by byte exception";
		}
	}
}
