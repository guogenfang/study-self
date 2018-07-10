package org.study.base.lang;

public class IntegerTest {
	public static void main(String[] args) {
		valueOf();
	}
	
	public static void valueOf(){
		Integer i1 = Integer.valueOf(128);
		Integer i2 = Integer.valueOf(128);
		System.out.println(i1 == i2);
		
		Integer i3 = Integer.valueOf(127);
		Integer i4 = Integer.valueOf(127);
		System.out.println(i3 == i4);
		
		int i5 = Integer.valueOf(127);
		int i6 = Integer.valueOf(127);
		System.out.println(i5 == i6);
		
		int i7 = Integer.valueOf(128);
		int i8 = Integer.valueOf(128);
		System.out.println(i7 == i8);
		
		int i9 = 128;
		int i10 = 128;
		System.out.println(i9 == i10);

		Integer i11 = 128;
		Integer i12 = 128;
		System.out.println(i11 == i12);
		
		Integer i13 = 127;
		Integer i14 = 127;
		System.out.println(i13 == i14);
		
	}
}
