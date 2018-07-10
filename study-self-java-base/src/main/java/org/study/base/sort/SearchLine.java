package org.study.base.sort;

public class SearchLine {
	
	public static Comparable linerSearch(Comparable data[],Comparable target){
		Comparable result = null;
		int index = 0;
		while(result == null && index < data.length){
			if(data[index].compareTo(target) == 0){
				result = data[index];
				index ++;
			}
		}
		return target;
	}
	
	public static void main(String[] args) {
		
	}
}
