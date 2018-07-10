package org.study.base.javanew;

public class LambdaTest01 {
	
	@FunctionalInterface
	public interface Compare {
		boolean compareAB(Integer p1, Integer p2);
	}

	public void sort(Integer[] ids, Compare compare) {
		Integer tmp = ids[0];
		for(int i = 1; i < ids.length; i ++ ){
			if(compare.compareAB(tmp, ids[i])){
				tmp = ids[i];
			}
		}
		System.out.println(tmp);
	}

	public static void main(String[] args) {
		Integer[] list = { 6, 2, 4, 9, 5, 3 };
		new LambdaTest01().sort(list, (a, b) -> {
			return a < b;
		});

	}
}
