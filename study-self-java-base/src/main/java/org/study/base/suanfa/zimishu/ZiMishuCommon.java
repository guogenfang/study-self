package org.study.base.suanfa.zimishu;

/**[简要描述]：
 * @author ggf
 * 2018年6月11日
 */
public class ZiMishuCommon {
	public static void main(String[] args) {
		
		for(int j = 1; j < 100000000; j++) {
			Integer num = j;
			Integer sum = 0;
			for(int i = 0; i < num.toString().length(); i++) {
				sum += mi(num.toString().length(), Integer.parseInt(num.toString().substring(i, i + 1)));
			}
			if(sum.equals(num)) {
				System.out.println(sum);
			}
		}
	}
	
	public static Integer mi(Integer n, Integer num) {
		Integer res = 1;
		for(int i = 0; i < n; i ++) {
			res = res * num;
		}
		return res;
	}
}
