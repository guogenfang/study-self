package org.study.base.json.jackjson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseObject {
	public static void main(String[] args) {
		parseJsonObject();
	}

	public static void parseJsonObject(){
		ObjectMapper mapper = new ObjectMapper();
		try{
			User user = mapper.readValue(new File("F://test//user2.json"), User.class);
			System.out.println(mapper.writeValueAsString(user));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static class User {
	    public enum Gender { MALE, FEMALE };

	    public static class Name {
	      private String _first, _last;

	      public String getFirst() { return _first; }
	      public String getLast() { return _last; }

	      public void setFirst(String s) { _first = s; }
	      public void setLast(String s) { _last = s; }
	    }

	    private Gender _gender;
	    private Name _name;
	    private boolean _isVerified;
	    private byte[] _userImage;

	    public Name getName() { return _name; }
	    public boolean isVerified() { return _isVerified; }
	    public Gender getGender() { return _gender; }
	    public byte[] getUserImage() { return _userImage; }

	    public void setName(Name n) { _name = n; }
	    public void setVerified(boolean b) { _isVerified = b; }
	    public void setGender(Gender g) { _gender = g; }
	    public void setUserImage(byte[] b) { _userImage = b; }
	}
}

