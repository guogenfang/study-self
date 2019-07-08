package org.study.base.jvm.gc;

import java.util.Date;

/**
 * [简要描述]:
 *
 * @Author ggf
 * @Date 2019/6/27 9:45
 **/
public class MyDate extends Date{

    private String var;

    MyDate(){}

    MyDate(String str){
        this.var = str;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("obj [Date: " + this.getTime() + "] is gc");
    }
}
