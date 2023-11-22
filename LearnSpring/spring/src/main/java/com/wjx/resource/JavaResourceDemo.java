package com.wjx.resource;

import cn.hutool.core.io.IoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JavaResourceDemo {

    public static void main(String[] args) throws IOException {
        //构建URL 指定资源的协议为http协议
        URL url = new URL("http://www.baidu.com");
        //打开资源连接
        URLConnection urlConnection = url.openConnection();
        //获取资源输入流
        InputStream inputStream = urlConnection.getInputStream();
        //通过hutool工具类读取流中数据
        String content = IoUtil.read(new InputStreamReader(inputStream));
        System.out.println(content);
    }

}