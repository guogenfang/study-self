package org.study.self.spring.boot.scheduled;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**[简要描述]：
 * @author ggf
 * 2018年2月10日
 */

@Configuration
public class Task01 {

	@Scheduled(fixedRate=2000)
    public void work() {
//       System.out.println("work--每2s执行：" + new Date());
    }

	
	@Scheduled(cron="0/2 * * * * ?")
	public void run() {
//		System.out.println("run--每2s执行：" + new Date());
	}
	
	//13点41分每2s执行
	@Scheduled(cron = "0/2 41 13 * * ?")
	public void run02() {
		System.out.println("run02--：" + new Date());
	}
	
	@Scheduled(cron = "0/2 42 12 * * ?")
	public void run03() {
		System.out.println("run03--：" + new Date());
	}
	
	//14点 每30s执行
	@Scheduled(cron = "0/30 * 14 * * ?")
	public void run04() {
		System.out.println("run04--：" + new Date());
	}
	
}
