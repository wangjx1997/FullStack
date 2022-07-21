package com.wjx.processor;

import com.wjx.bean.BeanLifeCycle;
import com.wjx.common.OrderCount;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author WangJX
 * @Date 2022/4/28 11:21
 * @Description
 */
@Component
public class CustomInstantiationBPP implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass == BeanLifeCycle.class) {
            int andIncrement = OrderCount.count.getAndIncrement();
            System.out.println(andIncrement + ":调用方法postProcessBeforeInstantiation...");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == BeanLifeCycle.class) {
            int andIncrement = OrderCount.count.getAndIncrement();
            System.out.println(andIncrement + ":调用方法postProcessAfterInstantiation...");
        }
        return false;
    }
}
