package com.wjx.format;

import java.text.MessageFormat;

public class MessageFormatDemo {

    public static void main(String[] args) {
        String message = MessageFormat.format("你好：{0}", "张三");
        System.out.println("message = " + message);
    }

}