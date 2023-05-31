package com.wjx.scheduledtest;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author WangJX
 * @Date 2023/1/16 17:35
 * @Description
 */
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override

            public void run() {

                // do something

            }

        }, 10000, 1000); // 10s 后调度一个周期为 1s 的定时任务

    }
}
