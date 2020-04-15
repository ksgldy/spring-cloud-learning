package cn.idea360.oracle.common;


public enum ApiCode {

    /**
     * 默认失败
     */
    FAILED(-1, "操作失败"),
    /**
     * 默认成功
     */
    SUCCESS(0, "执行成功"),


    FAILED_AUTHENTICATION(40001L, "不合法的凭证"),
    FAILED_AUTHORIZATION(40002L, "权限不足"),

    INVALID_TOKEN(40003L, "无效token"),
    INVALID_TOKEN_TIMEOUT(40004L, "token失效，请重新登录"),

    ILLEGAL_FILE_SIZE(40005L, "不合法的文件大小"),
    ILLEGAL_REQ_PARAMS_TYPE(40006L, "不合法的参数类型"),
    ILLEGAL_REQ_PARAMS(40007L, "不合法的请求字符"),
    ILLEGAL_REQ_FORMAT(40008L, "不合法的请求格式"),

    FAILED_REQ_COUNT_LIMIT(40009L, "接口调用超过限制"),
    FAILED_REQ_RATE_LIMIT(40010L, "API 调用太频繁，请稍候再试"),

    DATA_UNEXSITED(40011L, "数据不存在"),
    DATA_CONFLICT(40012L, "数据已存在"),

    ERR_SYSTEM(40013L, "系统错误"),

    ;

    private long code;
    private String msg;

    ApiCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
