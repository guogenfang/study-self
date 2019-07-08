package org.study.base.reflact.bean;

import java.util.Random;

/**
 * [简要描述]:
 *
 * @Author ggf
 * @Date 2018/11/13 13:55
 **/
public class A extends B implements IAInterface {

    private Integer id;

    private String name;

    public A() {
        System.out.println("init");
    }

    public A(Integer id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("init id:" + id + ",name:" + name);
    }

    @Override
    public Integer get(Integer id) {
        return new Random().nextInt(100);
    }

    public String getName(Integer id) {
        return "name=" + new Random().nextInt(100);
    }

    private void init() {
        System.out.println("init");
    }
}
