package mq.redis;

public class Order{
	private Integer id;
	private Integer time;
	
	public Integer getId() {
		return id;
	}
	
	public Integer getTime() {
		return time;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setTime(Integer time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "order detail ID is " + id + "and time is " + time ;
	}
}
