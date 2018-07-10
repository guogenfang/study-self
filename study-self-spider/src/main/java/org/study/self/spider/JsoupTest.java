package org.study.self.spider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class JsoupTest {
	static Map<String, String> nameMap = new TreeMap<>();
	public static void main(String[] args) throws Exception {
		String base = "http://www.bp17.com/0/0/";
		Jsoup.connect(base)
				.execute()
				.parse()
				.select("td[class=zl]")
				.select("a")
				.stream()
				.map(element -> {
					nameMap.put(base + element.attr("href"), element.text());
					return base + element.attr("href");
				})
				.forEach(map->{});
		for (String str : nameMap.keySet()) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						System.out.println(str);
						Jsoup.connect(str)
						.timeout(200000)
						.execute()
						.parse()
						.select("dd[id=contents]")
						.stream()
						.forEach(e -> {
							try {
								File file = new File("F://jsonData//" + nameMap.get(str) + ".txt");
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
								stream.write(e.toString()
										.replace("&nbsp;", "")
										.replace("<br>", "")
										.getBytes());
								stream.flush();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}).start();
		}
		
	}
}
