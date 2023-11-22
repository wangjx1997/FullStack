package com.wjx.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {

    public static void main(String[] args) {

        //获取ResourceBundle，第一个参数baseName就是我们的文件名称，第二个参数就是地区
        ResourceBundle chineseResourceBundle = ResourceBundle.getBundle("message", Locale.SIMPLIFIED_CHINESE);
        //根据name键取值
        String chineseName = chineseResourceBundle.getString("name");
        System.out.println("chineseName = " + chineseName);

        ResourceBundle englishResourceBundle = ResourceBundle.getBundle("message", Locale.ENGLISH);
        String englishName = englishResourceBundle.getString("name");
        System.out.println("englishName = " + englishName);

    }

}