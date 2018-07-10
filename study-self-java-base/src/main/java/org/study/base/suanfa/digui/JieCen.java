package org.study.base.suanfa.digui;

/**
 * n阶层
 * @author ggf
 *
 */
public class JieCen {
	public static void main(String[] args) {
		System.out.println(jie(5));
	}
	
	public static int jie(int x){
		System.out.println(x);
		if(x == 0)
			return 1;
		else
			return  x * jie(x - 1);			
	}
}
