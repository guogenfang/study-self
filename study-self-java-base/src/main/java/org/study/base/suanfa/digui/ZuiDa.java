package org.study.base.suanfa.digui;

public class ZuiDa {
	static int max(int[] array, int from) {
		if (from == array.length - 1) {
			return array[from];
		}
		return Math.max(array[from], max(array, from + 1));
	}

	public static void main(String[] args) {
		int[] a = { 38, 65, 97, 76, 13, 27, 49, 2, 78, 75, 56, 48, 89, 10, 3, 852 };
		System.out.println(max(a,0));
	}
}
