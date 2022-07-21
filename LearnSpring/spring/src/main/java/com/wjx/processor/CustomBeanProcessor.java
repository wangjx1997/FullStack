package com.wjx.processor;

import com.wjx.bean.BeanLifeCycle;
import com.wjx.common.OrderCount;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author WangJX
 * @Date 2022/4/28 11:07
 * @Description
 */
@Component
public class CustomBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == BeanLifeCycle.class) {
            int andIncrement = OrderCount.count.getAndIncrement();
            System.out.println(andIncrement + ":调用方法postProcessBeforeInitialization...");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == BeanLifeCycle.class) {
            int andIncrement = OrderCount.count.getAndIncrement();
            System.out.println(andIncrement + ":调用方法postProcessAfterInitialization...");
        }
        return bean;
    }

}
