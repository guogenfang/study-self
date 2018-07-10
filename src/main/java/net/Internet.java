package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class Internet {
	
	@Test
	public void address(){
		try {
			System.out.println(InetAddress.getByName("www.fayuan.com"));
			byte [] b = {114,55,49,(byte) 159};
			System.out.println(InetAddress.getByAddress(b));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
