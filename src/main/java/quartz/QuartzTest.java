package quartz;

import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;

import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
	
	@Test
	public void lesson() throws SchedulerException, IOException, InterruptedException{
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		JobDetail job = newJob(MyJob.class).withIdentity("job1", "group1").build();
		Trigger trigger = newTrigger()
			      .withIdentity("trigger1", "group1")
			      .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule()
			              .withIntervalInSeconds(2)
			              .repeatForever()).build();
		scheduler.scheduleJob(job, trigger);
		Thread.sleep(5000);
		scheduler.resumeTrigger(trigger.getKey());
		System.in.read();
	}
}
