package com.wjx.resource;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author WangJX
 * @Date 2023/8/3 11:10
 * @Description
 */
public class SpringResourceDemo {
    public static void main(String[] args) throws IOException {
        //构建资源
        Resource resource = new UrlResource("http://www.baidu.com");
//获取资源输入流
        InputStream inputStream = resource.getInputStream();



        //创建ResourceLoader
        ResourceLoader resourceLoader = new DefaultResourceLoader();
//获取资源
        Resource resourceby = resourceLoader.getResource("http://www.baidu.com");
    }
}
