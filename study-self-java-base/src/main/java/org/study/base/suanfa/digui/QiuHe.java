package org.study.base.suanfa.digui;

/**
 * 1+3+5+7+9+...... + n
 * @author ggf
 *
 */
public class QiuHe {
	public static void main(String[] args) {
		System.out.println(jie(100));
	}
	
	public static int jie(int x){
		int sum = x;
		if(x > 1){
			sum =  sum + jie(x - 2);			
		}
		return sum;
	}
}
