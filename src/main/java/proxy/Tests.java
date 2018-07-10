package proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstants;

public class Tests {
	public static void main(String[] args) throws Exception {
		User realUser = new User("sun","123");
		MyInvocationHandler handler = new MyInvocationHandler(realUser);
		 //获得代理类($Proxy0 extends Proxy implements IUser)的实例.
		//Object object = Proxy.newProxyInstance(User.class.getClassLoader(),new Class[]{IUser.class}, handler);
		IUser proxy = (IUser) Proxy.newProxyInstance(User.class.getClassLoader(),User.class.getInterfaces(), handler);
		proxy.getName();
		proxy.getId();
	}
	
	@Test
	public void security(){
		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
            ClassLoader ccl = Reflection.getCallerClass().getClassLoader();
            if (User.class.getClassLoader() == null && ccl != null) {
            	sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
            }
            ReflectUtil.checkProxyPackageAccess(ccl, User.class.getInterfaces());
        }
	}
}
