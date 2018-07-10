package org.study.base.json.jackjson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseArray {
public static void main(String[] args) throws Exception {
	JsonFactory f = new JsonFactory();
	ObjectMapper mapper = new ObjectMapper();
	String json = "[{\"foo\": \"bar\"},{\"foo\": \"biz\"}]";
	JsonParser jp = f.createParser(json);
	// advance stream to START_ARRAY first:
	jp.nextToken();
	// and then each time, advance to opening START_OBJECT
	while (jp.nextToken() == JsonToken.START_OBJECT) {
		Foo foobar = mapper.readValue(jp, Foo.class);
		System.out.println(mapper.writeValueAsString(foobar));
		// process
		// after binding, stream points to closing END_OBJECT
	}
}

	static class Foo {
		private String foo;
		
		public void setFoo(String foo) {
			this.foo = foo;
		}
		
		public String getFoo() {
			return foo;
		}
	}
}
