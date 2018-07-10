package model;


public class Singleton {
	private static Singleton singleton = new Singleton();
	private Class<?> ii;
	private String str;
	
	private Singleton() {
		
	}
	
	public static Singleton getInstance(){
		return singleton;
	}
	
	public static void main(String[] args) {
		Singleton sin = Singleton.getInstance();
		sin.str = "adfb";
		Singleton sin1 = Singleton.getInstance();
		System.out.println(sin1.str);
	}
}
