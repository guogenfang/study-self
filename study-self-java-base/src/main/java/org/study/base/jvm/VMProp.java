package org.study.base.jvm;

import java.util.Properties;
import java.util.Set;

/**
 * [简要描述]: vm option add properties -Djava.lang.Integer.IntegerCache.high=32768 -Dxsy.id=123456
 *
 * @Author ggf
 * @Date 2019/6/25 9:30
 **/
public class VMProp {
    public static void main(String[] args) {
//        System.setProperty("java.lang.Integer.IntegerCache.high","32768");
        String h = sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        System.out.println(h);
        System.out.println(System.getProperty("xsy.id"));

        Properties properties = System.getProperties();
        Set<Object> keys = properties.keySet();
        for (Object key : keys) {
            if (key instanceof String) {
                System.out.println("key :" + key + " and value  :" + System.getProperty((String) key));
            }
        }
    }
}
