package com.example.utils;

public class iputil {
	//http://172.25.147.206
	//192.168.1.104
	//192.168.1.101
	static final String scoket_ip = "172.25.147.206";
	static final String fwq_ip = "http://"+scoket_ip+":8080";
	public static String getFwqIp() {
		return fwq_ip;
	}
	public static String getScoketIp() {
		return scoket_ip;
	}
}
