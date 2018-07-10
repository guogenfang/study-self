package org.study.base.json.jackjson;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseJson {
	public static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) throws Exception {
		//String p = "[\"fFafdd\",\"ffdd\",\"f2Odd\", \"Even\", \"Odd\"]";
		String p = "[{\"name\":\"123ggf\",\"id\":\"2\"}]";
		
		Object[] param = mapper.readValue(p, Object[].class);
		System.out.println(Arrays.asList(param));
	}
}
