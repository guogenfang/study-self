package org.study.base.thread.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 
 *[简要描述]: 以下是一个先进先出 (first-in-first-out) 非重入锁类的框架。
 * @author ggf
 *
 * 2017年12月30日
 */
public class LockSupportDemo {
	private final AtomicBoolean locked = new AtomicBoolean(false);
	private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();

	public void lock() {
		boolean wasInterrupted = false;
		Thread current = Thread.currentThread();
		waiters.add(current);

		// Block while not first in queue or cannot acquire lock
		while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
			LockSupport.park(this);
			if (Thread.interrupted()) // ignore interrupts while waiting
				wasInterrupted = true;
		}

		waiters.remove();
		if (wasInterrupted) // reassert interrupt status on exit
			current.interrupt();
	}

	public void unlock() {
		locked.set(false);
		LockSupport.unpark(waiters.peek());
	}
	
	public static void main(String[] args) {
		new LockSupportDemo().lock();
	}

}
