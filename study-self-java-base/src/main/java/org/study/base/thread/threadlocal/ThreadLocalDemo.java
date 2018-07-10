package org.study.base.thread.threadlocal;

import java.util.Random;

/**
	1、在多线程的类（如ThreadDemo类）中，创建一个ThreadLocal对象threadXxx，用来保存线程间需要隔离处理的对象xxx。
	2、在ThreadDemo类中，创建一个获取要隔离访问的数据的方法getXxx()，
		在方法中判断，若ThreadLocal对象为null时候，应该new()一个隔离访问类型的对象，并强制转换为要应用的类型。
	3、在ThreadDemo类的run()方法中，通过getXxx()方法获取要操作的数据，
		这样可以保证每个线程对应一个数据对象，在任何时刻都操作的是这个对象。
 * @author ggf
 *
 */
public class ThreadLocalDemo implements Runnable{
	//创建线程局部变量studentLocal，在后面你会发现用来保存Student对象
    private final static ThreadLocal studentLocal = new ThreadLocal();
 
    public static void main(String[] agrs) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        Thread t1 = new Thread(demo, "a");
        Thread t2 = new Thread(demo, "b");
        t1.start();
        t2.start();
    }
 
    public void run() {
        accessStudent();
    }
 
    /**
     * 示例业务方法，用来测试
     */
    public void accessStudent() {
        //获取当前线程的名字
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running!");
        //产生一个随机数并打印
        Random random = new Random();
        int age = random.nextInt(100);
        System.out.println("thread " + currentThreadName + " set age to:" + age);
        //获取一个Student对象，并将随机数年龄插入到对象属性中
        Student student = getStudent();
        student.setAge(age);
        System.out.println("thread " + currentThreadName + " first read age is:" + student.getAge());
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("thread " + currentThreadName + " second read age is:" + student.getAge());
    }
 
    protected Student getStudent() {
        //获取本地线程变量并强制转换为Student类型
        Student student = (Student) studentLocal.get();
        //线程首次执行此方法的时候，studentLocal.get()肯定为null
        if (student == null) {
            //创建一个Student对象，并保存到本地线程变量studentLocal中
            student = new Student();
            studentLocal.set(student);
        }
        return student;
    }
}
