package com.wjx.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author WangJX
 * @Date 2023/7/21 17:23
 * @Description
 */
public class DateTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime min = LocalDateTime.MIN;
        System.out.println(min);
        LocalDateTime max = LocalDateTime.MAX;
        System.out.println(max);

        LocalDate now1 = LocalDate.now();
        LocalTime now2 = LocalTime.now();
    }
}
