package org.study.base.proxy.staticproxy;

import org.study.base.proxy.ISinger;
import org.study.base.proxy.Singer;

public class Test {
	public static void main(String[] args) {
		ISinger target = new Singer();//目标对象
		ISinger proxy = new SingerProxy(target);//代理对象
		proxy.singer();//执行代理方法
	}
}
