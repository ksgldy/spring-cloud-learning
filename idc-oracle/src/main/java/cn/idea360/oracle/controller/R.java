package cn.idea360.oracle.controller;

import java.io.Serializable;

public class R<T> implements Serializable {

    private long code;
    private T data;
    private String msg;

    public R() {
    }


    public static <T> R<T> ok() {
        return restResult(null, Code.SUCCESS);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, Code.SUCCESS);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, Code.FAILED.getCode(), msg);
    }

    public static <T> R<T> failed(Code errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> R<T> restResult(T data, Code errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> R<T> restResult(T data, long code, String msg) {
        R<T> apiResult = new R();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }


    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    enum Code{
        SUCCESS(0, "操作成功"),
        FAILED(-1, "操作失败"),
        ;
        private long code;
        private String msg;

        Code(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public long getCode() {
            return code;
        }
    }
}
