package jni;

public class Native {
	public native String getMsg(int number);
	
	public native void hasException() throws Exception;
}
