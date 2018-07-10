package model;

public class Single {
	private int a = 1;
	private static Single instence = new Single();

	private Single() {
	
	}
	
	public static Single getInstence() {
		return instence;
	}
	
	public int getA() {
		return a;
	}
	
	public void setA(int a) {
		this.a = a;
	}
}
