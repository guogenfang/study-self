package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler{
	private Object proxyObj;
	 
	public MyInvocationHandler() {}
	
	public MyInvocationHandler(Object obj){
		proxyObj = obj;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before the function \""+method.getName()+"\""); 
		Object ret = method.invoke(proxyObj, args); 
		System.out.println("after the function \""+method.getName()+"\""); 
		return ret;
	}

}
