package org.study.self.net;

import java.net.InetAddress;

public class BaseClass {
	public static void main(String[] args) throws Exception {
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(address);
	}
}
