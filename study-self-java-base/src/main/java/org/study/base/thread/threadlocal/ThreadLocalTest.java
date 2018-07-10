package org.study.base.thread.threadlocal;

/**
 * 变量是同一个，但是每个线程都使用同一个初始值，也就是使用同一个变量的一个新的副本。
 * 当使用ThreadLocal维护变量时，
 * ThreadLocal为每个使用该变量的线程提供独立的变量副本，
 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
　　从线程的角度看，目标变量就象是线程的本地变量，这也是类名中“Local”所要表达的意思。
　　所以，在Java中编写线程局部变量的代码相对来说要笨拙一些，
       因此造成线程局部变量没有在Java开发者中得到很好的普及。
 */
public class ThreadLocalTest {
	
	public static void main(String[] args) {  
    	ThreadLocalTest sn = new ThreadLocalTest();  
        // ③ 3个线程共享sn 对象，各自产生序列号  
        TestClient t1 = new TestClient(sn,"t1");  
        TestClient t2 = new TestClient(sn,"t2");  
        TestClient t3 = new TestClient(sn,"t3");  
        t1.start();  
        t2.start();  
        t3.start(); 
    }  
	
	// ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值 
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {  
        public Integer initialValue() {  
            return 1;  
        }  
    }; 
    
 // ②获取下一个序列值  
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);  
        return seqNum.get();  
    }  
  
    private static class TestClient extends Thread {  
        private ThreadLocalTest sn;  
        private String name;
  
        public TestClient(ThreadLocalTest sn,String name) {  
            this.sn = sn;  
            this.name = name;
        }  
  
        public void run() {  
            for (int i = 0; i < 2; i++) {  
                // ④每个线程打出3个序列值  
                System.out.println("thread[" + name + "|" + Thread.currentThread().getName() + "] --> sn["  
                         + sn.getNextNum() + "]");  
            }  
        }  
    }  
}
