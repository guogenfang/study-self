package org.study.base.json.gson;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class Parser {
	/* Gson gson = new GsonBuilder()
	 *     .registerTypeAdapter(Id.class, new IdTypeAdapter())
	 *     .enableComplexMapKeySerialization()
	 *     .serializeNulls()
	 *     .setDateFormat(DateFormat.LONG)
	 *     .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
	 *     .setPrettyPrinting()
	 *     .setVersion(1.0)
	 *     .create();
	 */
	GsonBuilder builder = new GsonBuilder();
	// Register an adapter to manage the date types as long values 
	{
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong()); 
			}
		});
	}
	
	Gson gsonByBuilder = builder.serializeNulls().create();
	
	Gson gson = new Gson();
	
	/**
	 * [简要描述]：对象转换json，包含属性null
	 * @author ggf
	 * @date 2017年6月17日 上午11:42:49
	 */
	@Test
	public void ObjToJsonWithNull(){
		InviteModel model = new InviteModel("123","192.168.60.1",null);
		System.out.println(gsonByBuilder.toJson(model));
		
	}
	
	/**
	 * [简要描述]：对象转换json，不包含属性null
	 * @author ggf
	 * @date 2017年6月17日 上午11:45:07
	 */
	@Test
	public void ObjToJsonWithoutNull(){
		//The generated JSON omits(忽略) all the fields that are null. 
		
		InviteModel model = new InviteModel("123","192.168.60.1",null);
		System.out.println(gson.toJson(model));
	}
	
	@Test
	public void StringToObj(){
		String string = "{'callid':'4002257389_1489472287157','host':'100015','hostName':'val','roomId':'111111','start_time':1489472287157}";
        InviteModel inviteModel = gsonByBuilder.fromJson(string,InviteModel.class);
        System.out.println(inviteModel.toString());
	}
}
