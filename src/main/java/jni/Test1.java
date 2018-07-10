package jni;

public class Test1 {
	static{
		//System.loadLibrary("msvcr100d");
		System.load("C://Users//ggf//Documents//Visual Studio 2010//Projects//test1//x64//Debug//test1.dll");
	}
	
	public static void main(String[] args) {
		Native nat = new Native();
		try {
			nat.hasException();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
