package org.study.self.spider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;

/**
 * [简要描述]：涩女郎漫画
 * 
 * @author ggf 2018年4月26日
 */
public class SeNVLang {
	static Map<String, String> nameMap = new TreeMap<>();

	public static void main(String[] args) throws Exception {
		ImageUtils.saveImg("F://test//a.jpg", ImageUtils.remoteImg("http://manhua1011-61-174-50-99.cdndm5.com/s/%E6%B6%A9%E5%A5%B3%E9%83%8E/%E6%B6%A9%E5%A5%B3%E9%83%8E_ch01/%E6%B6%A9%E5%A5%B3%E9%83%8E_%E7%AC%AC1%E5%8D%B7_0002.jpg"));
//		String base = "http://www.dm5.com/m40442";
//		Jsoup.connect(base)
//		.execute()
//		.parse()
//		.select("td[class=zl]")
//		.select("a")
//		.stream()
//		.map(element -> {
//			nameMap.put(base + element.attr("href"), element.text());
//			return base + element.attr("href");
//		})
//		.forEach(map->{});
		
//		for (int i = 1; i < 33; i++) {
//			String str = "http://manhua1011-61-174-50-99.cdndm5.com/s/%E6%B6%A9%E5%A5%B3%E9%83%8E/%E6%B6%A9%E5%A5%B3%E9%83%8E_ch01/%E6%B6%A9%E5%A5%B3%E9%83%8E_%E7%AC%AC1%E5%8D%B7_00"+ String.format("%02d", i) + ".jpg?cid=40442&key=da265e7265f66024805b1cebfd0c023e";
//			try {
//				File file = new File("F://test//senvlang//" + str + ".jpg");
//				try {
//					// 获取图片URL
//					URL url = new URL(str);
//					// 获得连接
//					URLConnection connection = url.openConnection();
//					// 设置10秒的相应时间
//					connection.setConnectTimeout(10 * 1000);
//					// 获得输入流
//					InputStream in = connection.getInputStream();
//					// 获得输出流
//					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
//					// 构建缓冲区
//					byte[] buf = new byte[1024];
//					int size;
//					// 写入到文件
//					while (-1 != (size = in.read(buf))) {
//						out.write(buf, 0, size);
//					}
//					out.close();
//					in.close();
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
	}
}
