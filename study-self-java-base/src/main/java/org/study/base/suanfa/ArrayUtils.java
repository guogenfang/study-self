package org.study.base.suanfa;

public class ArrayUtils {
	public static void print(int [] array){
		for(int i = 0; i < array.length; i++){
			System.out.print("---" + array[i]);
		}
	}
	
	public static void exchange(int from,int to,int[] array){
		int tmp = array[from];
		array[from] = array[to];
		array[to] = tmp;
	}
	
	public static boolean les(int first,int second,int[] array){
		if(array[first] < array[second]){
			return true;
		}
		else {
			return false;
		}
	}
}
