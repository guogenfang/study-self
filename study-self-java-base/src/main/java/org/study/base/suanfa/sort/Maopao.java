package org.study.base.suanfa.sort;

import org.study.base.suanfa.ArrayUtils;

public class Maopao {
	public static void main(String[] args) {
		int [] array = {10,31,22,23,14,35,66,17};
		for(int i = 0; i < array.length; i++){
			for(int n = i + 1; n < array.length; n++){
				exchange(array, i, n);
			}
		}
		ArrayUtils.print(array);
	}
	
	public static void exchange(int [] array,int i, int n) {
		if(array[i] > array[n]){
			int tmp = array[i];
			array[i] = array[n];
			array[n] = tmp;
		}
	}
}
