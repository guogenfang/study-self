package jvm;

public class ExampleEach {
	
	final int bb = 1111;
	
	static int width;
	/*
	 * 1、调用math类的random静态方法
	 * 2、把常量池中2压入栈
	 * 3、执行double类型的乘法
	 * 4、把double类型的数据转化为int类型
	 * 5、设置类中静态字段的值
	 */
	static int height = (int)(Math.random() * 2);
	
	static{
		width = (int)(Math.random() * 2);
	}
	public static void main(String[] args) {
		Integer i = new Integer(1);
		int j = 1;
		Integer c = 1;
		
		String af = new String("abc");
		String af1 = new String("abc");
		String a1 = "a1";
		String a2 = "a1";
	}
}
