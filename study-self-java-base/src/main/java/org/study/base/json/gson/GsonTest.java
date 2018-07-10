package org.study.base.json.gson;

public class GsonTest {
	public static void main(String[] args) {
		String string = "{\"id\":\"ggf\",\"name\":\"ggf\",\"roomId\":\"10000172\",\"extra\":null}";
        InviteModel inviteModel = GsonUtils.parseJsonWithGson(string,InviteModel.class);
        System.out.println(inviteModel.getExtra());
        System.out.println(inviteModel.toString());
	}
}
