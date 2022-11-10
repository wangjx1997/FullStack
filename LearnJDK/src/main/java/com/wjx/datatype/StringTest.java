package com.wjx.datatype;

import java.io.IOException;

/**
 * @Author WangJX
 * @Date 2022/10/1 20:26
 * @Description
 */
public class StringTest {
    public static void main(String[] args) throws IOException {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        System.in.read();
    }
}
