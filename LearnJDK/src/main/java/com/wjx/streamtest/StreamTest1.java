package com.wjx.streamtest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author WangJX
 * @Date 2023/2/8 14:58
 * @Description
 */
public class StreamTest1 {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("cat");
       Matcher m = p.matcher("one cat two cats in the yard");
       StringBuffer sb = new StringBuffer();
       while (m.find()) {
           m.appendReplacement(sb, "dog");
       }
       m.appendTail(sb);
       System.out.println(sb.toString());
    }
}
