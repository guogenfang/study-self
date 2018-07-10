package org.study.base.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientDemo {
	 private static String uri = "http://www.weather.com.cn/data/sk/101010100.html";
	 
	 @Test
	 public void postDemo() throws Exception{
		 System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		 System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		 System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
		 System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
		 System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");

		 HttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(uri);
			post.setHeader("abc","123456");
			StringEntity entity = new StringEntity("abc:123", "utf-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			
			String reString = EntityUtils.toString(response.getEntity());
			System.out.println(reString);
		}
	 
	 @Test
	 public void postArray() throws Exception{
		 System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		 System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		 System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
		 System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
		 System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
		 Map<String, Object> map = new HashMap<>();
		 String [] rtmp_url = {"http://rtmp:sf"};
		 map.put("rtmp_url[]", "rtmp://rtmp.wsdemo.zego.im/zegodemo/aaa");
		 HttpClientUtil.httpPostRequest("http://127.0.0.1:8080/post", map);
		}
}
