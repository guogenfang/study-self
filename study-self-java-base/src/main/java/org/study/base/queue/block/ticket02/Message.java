package org.study.base.queue.block.ticket02;

public interface Message {
	
	/**
	 * [简要描述]: 所有票已经生产好了
	 * @return
	 * @author ggf
	 * @date 2017年10月14日下午9:24:38
	 */
	void create(boolean success);
}
