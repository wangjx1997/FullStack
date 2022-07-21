package org.wjx.enums;

/**
 * @Author WangJX
 * @Date 2022/3/17 20:43
 * @Description
 */
public enum ResultEnum {
    SUCCESS(200,"success"),
    UNKNOW_ERROR(403,"系统异常");


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
