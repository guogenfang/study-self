package org.study.base.jvm;

import java.util.concurrent.locks.LockSupport;

/**
 * [简要描述]:
 *
 * @Author ggf
 * @Date 2019/6/28 8:52
 **/
public class DifferentStringAndStringBuffer {
    private final String abo = "abo";
    public static final String abe = "abe";
    public static void main(String[] args) {
        String str = "abc";
        StringBuffer buffer = new StringBuffer("abc");
        LockSupport.park();
    }
}
