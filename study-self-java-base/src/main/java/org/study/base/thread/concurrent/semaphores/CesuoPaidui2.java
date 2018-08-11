package org.study.base.thread.concurrent.semaphores;

import java.util.concurrent.Semaphore;

/**[简要描述]：
 * @author ggf
 * 2018年7月17日
 */
public class CesuoPaidui2 {
	
	private static final int MAX_AVAILABLE = 3;
	
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
	
	protected Toilet[] Toilets = {new Toilet(1), new Toilet(2), new Toilet(3)};
	
	protected boolean[] used = new boolean[MAX_AVAILABLE];
	
	public static void main(String[] args) {
		CesuoPaidui2 pool = new CesuoPaidui2();
		for (int i = 1; i < 10; i++) {
			final Integer n = i;
			Runnable task = ()->{
				try {
					System.out.println(n + "-等待 Toilet");
					Toilet item = pool.geToilet();
					Integer wait = (int)(Math.random() * 10000);
					System.out.println(n + "-进入 Toilet " + item + " need wait " + wait);
					Thread.sleep(wait);
					System.out.println(n + "-离开 Toilet " + item);
					pool.leaveToilet(item);
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			new Thread(task).start();
		}
	}
	
	public Toilet geToilet() throws Exception {
		available.acquire();
		return getNextAvailableItem();
	}
	
	public void leaveToilet(Object x) {
		if (markAsUnused(x))
			available.release();
	}
	
	protected synchronized Toilet getNextAvailableItem() {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			//如果没有被使用，设置启用状态
			if (!used[i]) {
				used[i] = true;
				return Toilets[i];
			}
		}
		return null; // not reached
	}

	protected synchronized boolean markAsUnused(Object item) {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (item.equals(Toilets[i])) {
				if (used[i]) {
					used[i] = false;
					return true;
				} else
					return false;
			}
		}
		return false;
	}
	
	class Toilet{
		private Integer id;
		
		public Toilet(Integer id) {
			this.id = id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		
		public Integer getId() {
			return id;
		}

		@Override
		public String toString() {
			return "Toilet [id=" + id + "]";
		}
		
	}
	
}
