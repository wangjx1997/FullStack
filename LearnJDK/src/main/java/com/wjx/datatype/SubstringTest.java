package com.wjx.datatype;

/**
 * @Author WangJX
 * @Date 2022/11/17 13:51
 * @Description
 */
public class SubstringTest {

    public static void main(String[] args) {
        String traNoM = "H00100202202240000325418140000";

        System.out.println(traNoM.substring(traNoM.length() - 12, traNoM.length() - 4));
    }
}
