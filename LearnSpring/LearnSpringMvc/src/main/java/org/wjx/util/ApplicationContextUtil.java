package org.wjx.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author WangJX
 * @Date 2022/3/17 15:08
 * @Description
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext var1) throws BeansException {

        setContext(var1);
    }

    private static void setContext(ApplicationContext var1) {
        applicationContext = var1;
    }

    private static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

}
