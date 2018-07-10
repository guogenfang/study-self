package org.study.base.date;

public class Test {
	public static void main(String[] args) {
		try {
			new Test().a2();
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void a2() throws Exception{
		a1();
	}
	
	public void a1() throws Exception{
		throw new Exception("aaa");
	}
}
