package jvm;

/**
 * java没有32与64的区分，java所有的定义都是规范化的，他不会收到cpu字长的影响，因此class文件不区分32与64，这些由jvm去处理了。
	常量池会存储所有的常量，即使是1也不例外，只是jdk在编译的时候，会优化class，将一些没有必要的常量指令化来节省class文件大小和内存加载的空间。ldc只是从常量池将数据检索出来，并无转换的过程，它将常量池的数据检索出来并压入操作数栈，然后其它指令讲操作数栈中此数出栈病存入方法栈局部变量索引中。
	其实java的执行引擎，与cpu执行引擎类似，大体上所有执行引擎的设计都是如此，java的常量池相当于内存数据，而java的指令指针相当于cpu的指令指针，它总是指向下一句代码。而局部变量相当于cpu的二级缓存，至于操作数栈，相当于寄存器。为什么说jvm是个虚拟机，因为他就像一个虚拟机一样运行。
 * @author ggf
 *
 */
public class IntTest {
	/**
	byte最大值127，小于、等于使用bipush整数压入栈
	short最大值32767，小于、等于使用sipush整数压入栈
	大于short类型数据的，虚拟机会在初始化时将值存入常量池中
		 0: bipush        127				//	将一个8位带符号整数127压入栈
		 2: istore_1						//	将int值127存入局部变量1
		 3: iload_1							//	从局部变量1中装载int类型值 
		 4: istore_2						//	将int值127存入局部变量2
		 5: sipush        32767				//	将一个16位带符号整数32767压入栈
		 8: istore_3						//	将int值32767存入局部变量3
		 9: ldc           #46               //	int 32768	把常量池中的项压入栈 
		11: istore        4					//	将int类型值存入局部变量 4
		13: bipush        123				//	将一个8位带符号整数123压入栈
		15: invokestatic  #47               //	Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
		18: astore        5					//	将引用类型或returnAddress类型值存入局部变量
	*/
	
	
	 public static void main(String[] args) {
		int ad = 127;
		int ae = ad;
		int ar = 32767;
		int aq = 32768;
		Integer aw = 123;
	}
}
