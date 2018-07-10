package org.study.self.analyse.java;

import java.net.URLClassLoader;

import org.junit.Test;

import sun.applet.AppletClassLoader;
import sun.misc.Launcher;

public class AppTest {
	
	/**
	 * [简要描述]：
	 * 1、用户自定义的类由应用(系统)类加载器AppClassLoader加载
	 * 2、AppClassLoader的父类是扩展类加载器ExtClassLoader
	 * 3 @see Launcher
	 * @author ggf
	 * @date 2017年5月22日
	 */
	@Test
	public void classLoader(){
		System.out.println(Launcher.getBootstrapClassPath());
		System.out.println(Launcher.getLauncher().getClassLoader());
		ClassLoader AppTest_loader = AppTest.class.getClassLoader();
		Class<?> AppTest_loader_parent = AppTest_loader.getClass();
		System.out.println(AppTest_loader_parent);
		while(AppTest_loader_parent != null){
			AppTest_loader_parent = AppTest_loader_parent.getSuperclass();
			System.out.println(AppTest_loader_parent);
		}
		System.out.println("================");
		System.out.println(AppTest_loader);
		while(AppTest_loader != null){
			AppTest_loader = AppTest_loader.getParent();
			System.out.println(AppTest_loader);
		}
	}
}
