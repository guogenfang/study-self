package org.study.base.ext;

import org.study.base.AbsClass;

public class SubClass extends AbsClass{
	 AbsClass abs;
	@Override
	protected void init() {
		System.out.println("sub init");
	}
	public static void main(String[] args) {
		AbsClass abs = new SubClass();
		abs.test();
	}
	@Override
	protected void add() {
		// TODO Auto-generated method stub
		
	}
}
