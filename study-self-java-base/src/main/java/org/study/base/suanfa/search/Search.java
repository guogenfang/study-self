package org.study.base.suanfa.search;


public class Search {
	public static void main(String[] args) {
		int key = 5;
		int [] array = {0,1,2,3,4,5,6,7};
		//commonSearch(key, array);
		erFenSearch(key,array);
	}
	
	/**
	 * 普通遍历查找
	 * @param key 要查找的数
	 * @param array
	 */
	public static void commonSearch(int key,int [] array){
		for(int i = 0; i < array.length; i++){
			if(array[i] == key){
				System.out.println(i);
				break;
			}
		}
	}
	
	/**
	 * 二分法查找
	 * @param key 要查找的数
	 * @param array
	 */
	public static void erFenSearch(int key,int [] array){
		int mid = 0;
		int start = 0;
		int end = array.length - 1;
	
		while(key != array[mid]){
			mid = start + (end - start)/2;//取中间数
			System.out.println("mid:" + mid + ",start:" + start + ",end:" + end);
			if(key < array[mid]){
				end = mid - 1;
			}
			else if(key > array[mid]){
				start = mid + 1;
			}
			else {
				System.out.println(key);
				//break;
			}
		}
	}
}
