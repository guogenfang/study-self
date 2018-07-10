package org.study.base.suanfa.sort;

import org.study.base.suanfa.ArrayUtils;

public class XuanZe {
	public static void main(String[] args) {
		int [] array = {10,31,22,23,14,75,66,17};
		for(int i = 0; i < array.length; i++){
			int min = i;
			for(int n = i + 1; n < array.length; n++){
				//选出剩余位置的最小值
				if(array[n] < array[min]){
					min = n;
				}
			}
			ArrayUtils.exchange(i, min, array);
		}
		ArrayUtils.print(array);
	}
}
