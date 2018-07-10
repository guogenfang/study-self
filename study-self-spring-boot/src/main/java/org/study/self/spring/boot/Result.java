package org.study.self.spring.boot;

public class Result {
    private int code;
    private Object data;

    public final static Result OK = new Result(200);
    public final static Result UNAUTHORIZED = new Result(401);
    public final static Result FORBIDDEN = new Result(403);
    public final static Result NOT_FOUND = new Result(404);
    public final static Result ERROR = new Result(500);

    private Result(int code) {
        this.code = code;
    }

    private Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Result ok(Object data) {
        return new Result(200, data);
    }

    public static Result error(Object data) {
        return new Result(500, data);
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}