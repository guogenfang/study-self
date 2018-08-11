package org.study.self.spider;

import java.io.IOException;

import org.jsoup.Jsoup;

/**[简要描述]：
 * @author ggf
 * 2018年7月10日
 */
public class CourtCodeParse {
	public static void main(String[] args) throws Exception {
		String base = "http://127.0.0.1:8080/court_code.html";
		Jsoup.connect(base)
				.execute()
				.parse()
				.select("div.stl_view")
				.stream()
				.map(e -> {
					System.out.println(e);
					return e;
				});
	}

}
