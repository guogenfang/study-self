package org.study.self.jooq.model;

/**
 * [简要描述]：数据响应
 * @author ggf
 * @date 2017年8月15日
 */
public class Result {
	
	private Integer code;
	
	private Object data;
	
	public final static Result OK = new Result(200);
    public final static Result ERROR = new Result(500);

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Result ok(Object data) {
        return new Result(200, data);
    }

    public static Result error(Object data) {
        return new Result(500, data);
    }
    
	public Result(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
}
