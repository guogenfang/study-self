package mq.redis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class OrderHander {
	private AtomicLong currentOrderId = new AtomicLong(0);
	private Jedis jedis = new Jedis("127.0.0.1", 6379);
	public static final String key = "orders";
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void iniData() throws Exception{
		TimerTask task = new TimerTask() {  
			@Override  
			public void run() {  
				Map<String, Double> scores = new HashMap<String, Double>();
				for(int i = 0; i< 20; i++){
					Calendar calc = Calendar.getInstance();
					calc.add(Calendar.SECOND, 60);
					currentOrderId.getAndIncrement();
					try{
						scores.put(currentOrderId.toString(), (double)removeMillis(calc));
						jedis.zadd(key, scores);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 400 * 1000;  
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
	}
	
	//转换毫秒数
	private Long removeMillis(Calendar cal) throws Exception{
		String data = dateFormat.format(cal.getTime());
		Date now = dateFormat.parse(data);
		System.out.println("-------当前时间：" + now + "---毫秒数 ---" + now.getTime());
		return now.getTime();
	}
	
	
	private void consumer() throws Exception{
		TimerTask task = new TimerTask() {  
			@Override  
			public void run() {  
				Calendar calc = Calendar.getInstance();
				long score;
				try {
					score = removeMillis(calc);
					Set<String> orders= jedis.zrangeByScore(key, 0, score);
					if(orders.isEmpty() || orders.size() == 0){
						System.out.println("--------暂时没有订单，时间：" + calc.getTime());
					}
					else
					{
						for (String str : orders) {
							System.out.println("======处理订单，订单号：" + str);
							Long result = jedis.zrem(key, str);
							System.out.println("=======处理完成--,处理结果" + result);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 5 * 1000;  
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
	}
	public static void main(String[] args) throws Exception {
		OrderHander orderHander = new OrderHander();
		//orderHander.iniData();
		orderHander.consumer();
	}
}
