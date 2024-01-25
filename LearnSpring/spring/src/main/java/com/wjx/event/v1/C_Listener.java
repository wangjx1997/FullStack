package com.wjx.event.v1;

import com.wjx.event.pojo.C;
import org.springframework.context.ApplicationListener;

public class C_Listener implements ApplicationListener<C> {

        @Override
        public void onApplicationEvent(C event) {
            System.out.println("实现ApplicationListener"+event);
        }
    }