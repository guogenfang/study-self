package org.study.base.jvm;

/**
 * [简要描述]:
 *  byte最大值127，小于、等于使用bipush整数压入栈
 * 	short最大值32767，小于、等于使用sipush整数压入栈
 * 	大于short类型数据的，虚拟机会在初始化时将值存入常量池
 *
 * Integer.valueOf -128~127
 * if (i >= IntegerCache.low && i <= IntegerCache.high)
 *     return IntegerCache.cache[i + (-IntegerCache.low)];
 * return new Integer(i);
 *
 * @Author ggf
 * @Date 2019/6/21 21:08
 **/
public class DefinitionClass {
    /**
     *  sipush 32767 将16位带符号整数（32767）压入栈
     *  invokestatic #2 <java/lang/Integer.valueOf>
     *  putfield #3 <org/study/base/jvm/DefinitionClass.abb>
     */
    private Integer abb = 32767;

    /**
     *  sipush 32767
     *  invokestatic #2 <java/lang/Integer.valueOf>
     *  putstatic #10 <org/study/base/jvm/DefinitionClass.abc>
     */
    private static Integer abc = 32767;
    /**
     * static 变量 cinit执行
     *   ldc #11 <32768> 把常量池中的项（32768）压入栈
     *  invokestatic #2 <java/lang/Integer.valueOf>
     *  putstatic #12 <org/study/base/jvm/DefinitionClass.abd>
     **/
    private static Integer abd = 32768;

    /**
     *  sipush 128
     *  invokestatic #2 <java/lang/Integer.valueOf>
     *  putstatic #12 <org/study/base/jvm/DefinitionClass.abe>
     **/
     private static Integer abe = 128;

    /**
     *  bipush 127 将一个8位带符号整数压入栈
     *  invokestatic #3 <java/lang/Integer.valueOf>
     *  putstatic #13 <org/study/base/jvm/DefinitionClass.abf>
     */
    private static Integer abf = 127;

    public final String str = "abc";

    /**
     *  iconst_1 将int类型常量1压入栈
     *  invokestatic #3 <java/lang/Integer.valueOf> 调用类（静态）方法
     *  astore_1 将引用类型或returnAddress类型值存入局部变量1（f）
     *  aload_1 从局部变量1中装载引用类型值
     *  astore_2 将引用类型或returnAddress类型值存入局部变量2
     *  aload_1
     *  invokevirtual #7 <java/lang/Integer.intValue>
     *  iconst_1
     *  iadd 执行int类型的加法 f++
     *  invokestatic #3 <java/lang/Integer.valueOf>
     *  dup 复制栈顶部一个字长内容
     *  astore_1 将f+1后的内容存入局部变量1中
     *  astore_3 将f+1后的内容存入局部变量3中
     *  aload_2  从局部变量2中装载引用类型值
     *  pop 弹出栈顶端（局部变量2）一个字长的内容
     *  iconst_0
     *  invokestatic #8 <java/lang/Boolean.valueOf>
     *  areturn
     *
     **/
    public Boolean ggg(){
        Integer f = 1;
        f++;
        System.out.println(f);
        return false;
    }

    public Boolean fff(){
        Integer ff = 1;
        ++ff;
        System.out.println(ff);
        return false;
    }

    public static void main(String[] args) {
        int abc = 1245;

        /**
         *  sipush 128
         *  invokestatic #2 <java/lang/Integer.valueOf>
         *  astore_2 将引用类型或returnAddress类型值存入局部变量2（变量a）
         */
        Integer a = 128;

        /**
         *  sipush 128
         *  invokestatic #2 <java/lang/Integer.valueOf>
         *  astore_3 将引用类型或returnAddress类型值存入局部变量2（变量b）
         */
        Integer b = 128;

        /**
         *  bipush 127
         *  invokestatic #2 <java/lang/Integer.valueOf>
         *  astore 4
         */
        Integer c = 127;
        Integer d = 127;
    }

}
