package com.wjx.bean;

import com.wjx.common.OrderCount;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author WangJX
 * @Date 2022/4/27 18:37
 * @Description
 */
public class BeanLifeCycle implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware {


    public BeanLifeCycle() {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":调用Bean的构造器方法(constructor)");
    }


    @Override
    public void setBeanName(String name) {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":获取定义的Bean的名称为:" + name);

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":获取定义的Bean所在的BeanFactory" + beanFactory.getClass().getSimpleName());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":获取定义的Bean所在的ApplicationContext,为" + applicationContext.getClass().getSimpleName());
    }


    @PostConstruct
    public void postConstruct() {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":调用Bean的函数:方法包含注解@PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":调用Bean的函数实现接口InitializingBean,重写方法afterPropertiesSet)");
    }


    public void initMethod() {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":调用Bean的定义的时候声明的initMethod");
    }

    @PreDestroy
    public void preDestroy() {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":调用Bean的函数:方法包含注解@PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":调用Bean的函数实现接口DisposableBean,重写方法destory");
    }

    public void destroyMethod() {
        int andIncrement = OrderCount.count.getAndIncrement();
        System.out.println(andIncrement+":调用Bean的定义的时候声明的destroyMethod)");
    }
}
