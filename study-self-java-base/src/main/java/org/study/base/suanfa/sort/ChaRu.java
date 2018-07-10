package org.study.base.suanfa.sort;

import org.study.base.suanfa.ArrayUtils;

public class ChaRu {
	public static void main(String[] args) {
		int [] array = {10,31,22,23,14,75,66,17};
		for(int i = 1; i < array.length; i++){
			for(int j = i; j > 0;j--){
				if(array[j] < array[j - 1]){
					ArrayUtils.exchange(j, j - 1, array);
				}
			}
			
		}
		ArrayUtils.print(array);
	}
}
