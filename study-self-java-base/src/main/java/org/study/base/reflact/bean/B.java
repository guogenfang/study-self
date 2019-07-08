package org.study.base.reflact.bean;

import java.util.Random;

/**
 * [简要描述]:
 *
 * @Author ggf
 * @Date 2018/11/13 14:04
 **/
public class B {
    public void getAge(Integer id){
        System.out.println("age:" + new Random().nextInt(100));
    }
}
