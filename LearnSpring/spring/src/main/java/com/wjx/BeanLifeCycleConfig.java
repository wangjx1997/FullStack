package com.wjx;

import com.wjx.bean.BeanLifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangJX
 * @Date 2022/4/28 11:07
 * @Description
 */
@Configuration
@ComponentScan
public class BeanLifeCycleConfig {
    @Bean(name = "testBeanName",initMethod = "initMethod", destroyMethod = "destroyMethod")
    public BeanLifeCycle beanLifeCycle() {
        return new BeanLifeCycle();
    }
}
