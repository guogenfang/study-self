package org.study.base.jvm;

/**
 * [简要描述]:
 *
 * @Author ggf
 * @Date 2019/6/30 23:10
 **/
public class SingleCase01 {
    private static SingleCase01 sc01 = new SingleCase01();

    private SingleCase01() {
    }

    public static SingleCase01 getInstance() {
        return sc01;
    }

    public static void main(String[] args) {
        SingleCase01.getInstance();
        String abcd = "ffff";
    }
}
