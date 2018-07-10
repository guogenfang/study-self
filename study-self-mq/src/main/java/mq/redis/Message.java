package mq.redis;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	
	public Message() {}
	
	Message(Integer id,String content){
		this.id = id;
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
