package org.study.self.spider;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String... args) throws Exception {
		Map<String, String> total = new HashMap<String,String>();
		for(int i=1;i<5;i++){
			Jsoup.connect("http://www.ximalaya.com/20984485/album/331804?page="+i)
			.execute()
			.parse()
			.select("li[sound_id]")
			.stream()
			.map(element -> element.attr("sound_id"))
			.map(id -> {
				try {
					return Jsoup.connect("http://www.ximalaya.com/tracks/"+id+".json").ignoreContentType(true).execute().body();
				} catch (Exception e) {
					System.err.println(e);
					return null;
				}
			})
			.map(body -> {
				try {
					return objectMapper.readValue(body, Map.class);
				} catch (Exception e) {
					System.err.println(e);
					return null;
				}
			})
			.forEach(map -> total.put(map.get("play_path").toString().split("/")[7],map.get("title").toString()));
		}
		
		File dir = new File("g://aa//");
		File [] files = dir.listFiles();
		
		for (File file : files) {
			//System.out.println(file.getName() + "  "+ total.get(file.getName()));
			File ff = new File("g://aud//"+total.get(file.getName())+ ".m4a");
			if(ff.exists()){
				file.renameTo(new File("g://aud//"+total.get(file.getName())+ "(2).m4a"));
			}else{
				file.renameTo(ff);
			}
			
		}
//		String body = Jsoup.connect("http://www.ximalaya.com/tracks/11428741.json").ignoreContentType(true).execute().body();
//		System.out.println(body);
	}

}
