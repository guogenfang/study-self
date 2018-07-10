package org.study.base.proxy.staticproxy;

import org.study.base.proxy.ISinger;

public class SingerProxy implements ISinger{
	
	private ISinger target;
	
	public SingerProxy(ISinger target){
		this.target = target;
	}
	
	@Override
	public void singer() {
		System.out.println("问好");
		target.singer();
		System.out.println("谢谢");
	}
}
