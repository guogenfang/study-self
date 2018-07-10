package mq.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class QuestionTask implements Delayed{

	private long time;
	
	private String question;
	
	
	public QuestionTask(long timeout, String question) {
		this.time = System.nanoTime() + timeout;
		this.question = question;
	}
	
	public int compareTo(Delayed o) {
		 if(o == null || ! (o instanceof QuestionTask)) return 1;
	        if(o == this) return 0; 
	        QuestionTask s = (QuestionTask)o;
	        if (this.time > s.time) {
	            return 1;
	        }
	        else if (this.time == s.time) {
	            return 0;
	        }else {
	            return -1;
	        }
	}

	/**
	 * 返回与此对象相关的剩余延迟时间，以给定的时间单位表示
	 */
	public long getDelay(TimeUnit unit) {
		return unit.convert(this.time - System.nanoTime() , TimeUnit.NANOSECONDS);
	}

	public String getQuestion() {
		return question;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
}
