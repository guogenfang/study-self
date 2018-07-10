package mq.redis;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RedisTest {
	private Jedis jedis; 
	@Before
 	public void setup() {
		//连接redis服务器
	 	jedis = new Jedis("121.40.89.8", 6379);
	 	//权限认证
	 	//jedis.auth("root");  
 	}
	
	//@Test
	public void getAllKeys(){
		for (String key : jedis.keys("*")) {
			System.out.println(key);
		}
	}
	
	//@Test
	public void getAllBykey(){
		Set<String> sets = jedis.smembers("pushkey");
		for(String str : sets){
			System.out.println(str);
		}
	}
	
	@Test
	public void search(){
		ScanParams param = new ScanParams();
		param.match("*id:1009*");
		ScanResult<String> res = jedis.sscan("pushkey", "0",param);
		List<String> list = res.getResult();
		System.out.println(list.size());
		for(String str : list){
			System.out.println(str);
		}
	}
	
	@Test
	public void add(){
		jedis.sadd("pushkey", "{id:1009,deviceType:1,channelId:904545232344}");
	}
	
	/**
	* redis存储字符串
	*/
	//@Test
	public void testString() {
		//-----添加数据----------  
		jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin 
		System.out.println(jedis.get("name"));//执行结果：xinxin  
	  
		jedis.append("name", " is my lover"); //拼接
		System.out.println(jedis.get("name")); 
	  
		jedis.del("name");  //删除某个键
		System.out.println(jedis.get("name"));
		//设置多个键值对
		jedis.mset("name","liuling","age","23","qq","476777XXX");
		jedis.incr("age"); //进行加1操作
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
	}
	   
	public static void main(String[] args) {
		String str = "{id:%s,deviceType:%s,channelId:%s}";
		str = String.format(str, "10001","1","7846130");
		System.out.println(str);
	}
}
