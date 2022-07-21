package org.wjx.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * @Author WangJX
 * @Date 2022/3/17 20:43
 * @Description 返回结果格式统一
 */
public class ResultVo {

    //错误码
    private Integer code;

    //提示信息
    private String msg;

    //具体的内容  不为空才序列化
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
