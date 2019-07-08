package org.study.base.reflact;

import java.lang.reflect.Field;

/**
 * [简要描述]:
 *
 * @Author ggf
 * @Date 2018/11/13 9:47
 **/
public class PrivateFieldTest {

    public static void main(String[] args) throws IllegalAccessException {
        TempObj tempObj = new TempObj("pri");
        Field f1 = getFieldByForeach(tempObj, "str1");
        System.out.println("-------------------------");
        Field f2 = getField(tempObj, "str1");

        System.out.println(f1.equals(f2));
        System.out.println(f1.get(tempObj));

    }

    private static Field getField(final Object object, String fieldName) {
        Object obj = object;
        if (obj == null) {
            return null;
        }

        Class<?> clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if(!field.isAccessible()){
                field.setAccessible(true);
            }
            return field;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Field getFieldByForeach(final Object object, String fieldName) {
        Object obj = object;
        if (obj == null) {
            return null;
        }

        Class<?> clazz = obj.getClass();
        try {
            while (clazz != null) {
                for (Field f : clazz.getDeclaredFields()) {
                    if(!f.isAccessible()){
                        f.setAccessible(true);
                    }
                    System.out.println(f.isAccessible() + "--" + f.getName() + "--" + f.get(obj));
                    if(fieldName != null && fieldName.equals(f.getName())){
                        return f;
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
