package org.study.base.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		for (int i = 0; i < 4; i++) {
			final int n = i;
			new Thread(() -> {
				lock.lock();
				print(n);
				lock.unlock();
			}).start();
		}
	}

	public static void print(int id) {
		for (int i = 0; i < 8; i++) {
			System.out.println(id + "----" + i);
		}
	};

}
