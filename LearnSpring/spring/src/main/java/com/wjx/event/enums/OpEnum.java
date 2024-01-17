package com.wjx.event.enums;

/**
 * @Author WangJX
 * @Date 2024/1/14 11:02
 * @Description 操作类型
 */
public enum OpEnum {
    ADD("add","添加"),
    DEL("del","删除"),
    UPDATE("update","更新");


    private String type;
    private String des;
    OpEnum(String type, String des) {
        this.type = type;
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public String getDes() {
        return des;
    }
}
