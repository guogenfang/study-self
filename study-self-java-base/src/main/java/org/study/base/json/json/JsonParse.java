package org.study.base.json.json;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonParse {
	public static void main(String[] args) throws Exception {
		String js = "{\"signalingServer\": {\"http\": \"http: //121.40.89.8: 9000\",\"https\": \"https: //121.40.89.8: 9001\"}}";
		 JSONObject paramsObj = new JSONObject(js);
         JSONObject signalingServer = paramsObj.getJSONObject("signalingServer");
         System.out.println(signalingServer.getString("http"));
         System.out.println(JsonParse.class.getClassLoader().getResource("sutdy-self"));
	
        String str = "{\"2334\":\"阿道夫\",\"23343\":\"阿道夫\",\"23235\":\"阿道夫\"}";
 		JSONTokener jsonParser = new JSONTokener(str);
 		JSONObject reg_response = (JSONObject) jsonParser.nextValue();
        System.out.println(reg_response.getString("23343"));
         
	}
}
