package org.study.base.reflact.bean;

import org.study.base.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * [简要描述]: 反射对象操作
 *
 * @Author ggf
 * @Date 2018/11/13 13:57
 **/
public class BeanUtils {
    public static void main(String[] args) throws Exception {
//        A a1 = new A();
//        A a2 = instantiate(A.class);

        A a3 = instantiateClass(A.class);

        Object[] params = {1, "ggf"};
        A a4 = instantiateClass(A.class, params);
    }

    public static void copyProperties(Object source, Object target) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();

//        PropertyDescriptor[] targetPds = actualEditable.pro
    }

    @Deprecated
    public static <T> T instantiate(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        Assert.notNull(clazz, "Class must not be null");
        if (clazz.isInterface()) {
            System.out.println("接口");
            return null;
        }
        return clazz.newInstance();
    }

    public static <T> T instantiateClass(Class<T> clazz, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (clazz.isInterface()) {
            System.out.println("接口");
            return null;
        }

        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        Constructor<T> ctor = clazz.getDeclaredConstructor(parameterTypes);
        makeAccessible(ctor);
        return ctor.newInstance(args);
    }

    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers()) ||
                !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
    }

    public static void getInterfaces(A a) {
        Arrays.stream(a.getClass().getInterfaces()).forEach(i -> {
            System.out.println(i);
        });
    }

    /**
     * [简要描述]：获取所有方法
     *
     * @return void
     * @Author ggf
     * @Date 14:08 2018/11/13
     * @Param [a]
     **/
    public static void getAllMethod(A a) {
        Class<?> cls = a.getClass();
        while (cls != null) {
            Arrays.stream(cls.getClass().getDeclaredMethods()).forEach(m -> {
                System.out.println(m);
            });
            cls = cls.getSuperclass();
        }
    }

    /**
     * [简要描述]：获取当前类的所有方法
     *
     * @return void
     * @Author ggf
     * @Date 14:08 2018/11/13
     * @Param [a]
     **/
    public static void getCurrentClassMethod(A a) {
        Arrays.stream(a.getClass().getDeclaredMethods()).forEach(m -> {
            System.out.println(m);
        });
    }
}
