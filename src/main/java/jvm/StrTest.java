package jvm;

/**
 *  Code:
    0: aload_0
    1: invokespecial #11                 // Method java/lang/Object."<init>":()V
    4: aload_0							 // 从局部变量0中装载引用类型值 
    5: ldc           #13                 // String tt 把常量池中的项压入栈
    7: putfield      #15                 // Field s1:Ljava/lang/String;设置对象中字段的值 
   10: aload_0
   11: ldc           #17                 // String m
   13: putfield      #19                 // Field s2:Ljava/lang/String;
   16: return
   
 public static void main(java.lang.String[]);
   Code:
      0: ldc           #13                 // String tt
      2: astore_1						   // 出操作数栈，赋值给变量1,即s3
      3: new           #29                 // class java/lang/String
      6: dup
      7: ldc           #13                 // String tt
      9: invokespecial #31                 // Method java/lang/String."<init>":(Ljava/lang/String;)V
     12: astore_2
     13: return
 */
public class StrTest {
	static String s1="11";
	static String s2="m";
	public static void main(String[] args) {
		String s3 = "11";
		System.out.println(s1 == s3);
		String s4 = new String("tt");
		String s5 = new String("tt");
		System.out.println(s4.equals(s5));
		System.out.println(s5.hashCode());
		System.out.println(s4 == s5);
	}
}
