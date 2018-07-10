import java.io.File;

import org.json.JSONObject;

public class Ta {
	public static void main(String[] args) throws Exception {
		String js = "{\"signalingServer\": {\"http\": \"http: //121.40.89.8: 9000\",\"https\": \"https: //121.40.89.8: 9001\"}}";
		 JSONObject paramsObj = new JSONObject(js);
         JSONObject signalingServer = paramsObj.getJSONObject("signalingServer");
         System.out.println(signalingServer.getString("http"));
         File file = new File("/redis_config.xml");
         System.out.println(file.exists());
         System.out.println(Ta.class.getClassLoader().getResource("sutdy-self"));
	}
}
