package com.wjx.enumt;

/**
 * @Author WangJX
 * @Date 2022/6/10 17:22
 * @Description
 */
public class EnumTest {



    public static void main(String[] args) {
        try {
            throw new Ex(T2.A);
        } catch (Exception ex) {
//            ex.printStackTrace();
            Ex ex1;
            if (ex instanceof Ex) {
                ex1 = (Ex) ex;
                System.out.println(T2.A.equals(ex1.getAnEnum()));
            }
        }


    }
}
