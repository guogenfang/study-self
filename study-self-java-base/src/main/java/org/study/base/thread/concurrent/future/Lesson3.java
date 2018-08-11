package org.study.base.thread.concurrent.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**[简要描述]：
 * @author ggf
 * 2018年7月18日
 */
public class Lesson3 {

private static List<Future<String>> futureList = new ArrayList<Future<String>>();
    
static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
    	Lesson3 t = new Lesson3();
        t.generate(100);
        t.getResult(futureList);
        service.shutdown();
    }

    /**
     * 生成指定数量的线程，都放入future数组
     * 
     * @param threadNum
     * @param fList
     */
    public void generate(int threadNum) {
        for (int i = 0; i < threadNum; i++) {
            final int n = i;
            Callable<String> job = new Callable<String>() {
                @Override
                public String call() throws Exception {
                	System.out.println("thread-" + n + " start");
                    Thread.sleep(new Random().nextInt(4000));
                    System.out.println("thread-" + n + " end");
                    return "thread-" + n;
                }
            };
            futureList.add(service.submit(job));
        }
    }

    /**
     * 从future中获取线程结果，打印结果
     * 
     * @param fList
     */
    public void getResult(List<Future<String>> fList) {
//        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(getCollectJob(fList));
    }

    /**
     * 生成结果收集线程对象
     * 
     * @param fList
     * @return
     */
    public Runnable getCollectJob(final List<Future<String>> fList) {
        return new Runnable() {
            @Override
            public void run() {
                for (Future<String> future : fList) {
                    try {
                        while (true) {
                            if (future.isDone()) {
                                System.out.println("Result:" + future.get());
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
