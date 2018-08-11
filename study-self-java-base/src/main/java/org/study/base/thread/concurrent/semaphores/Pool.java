package org.study.base.thread.concurrent.semaphores;

import java.util.concurrent.Semaphore;

/**
 * [简要描述]：信号量通常用于限制可以访问某些(物理或逻辑)资源的线程数。例如，下面是一个类，它使用信号量来控制对项目池的访问：
 * 
 * @author ggf 2018年7月17日
 */
public class Pool {
	
	public static void main(String[] args) throws Exception {
		Pool pool = new Pool();
		for (int i = 0; i < 7; i++) {
			Runnable task = ()->{
				try {
					Object item = pool.getItem();
					System.out.println(Thread.currentThread() + "---item: " + item);
					Thread.sleep(100);
					pool.putItem(item);
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			new Thread(task).start();
		}
	}
	
	
	private static final int MAX_AVAILABLE = 3;
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

	
	public Object getItem() throws InterruptedException {
		available.acquire();
		return getNextAvailableItem();
	}

	public void putItem(Object x) {
		if (markAsUnused(x))
			available.release();
	}

	// Not a particularly efficient data structure; just for demo

	// protected Object[] items = ... whatever kinds of items being managed
	protected Object[] items = {1,2,3};
	
	protected boolean[] used = new boolean[MAX_AVAILABLE];

	protected synchronized Object getNextAvailableItem() {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			//如果没有被使用，设置启用状态
			if (!used[i]) {
				used[i] = true;
				return items[i];
			}
		}
		return null; // not reached
	}

	protected synchronized boolean markAsUnused(Object item) {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (item == items[i]) {
				if (used[i]) {
					used[i] = false;
					return true;
				} else
					return false;
			}
		}
		return false;
	}
}
