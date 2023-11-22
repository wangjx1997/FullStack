package com.wjx.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.password}")
    private String redisPassword;
    @Value("${spring.redis.port}")
    private String port;

    @Bean
    @ConditionalOnMissingBean
    public RedissonClient redissonClient() {
        Config config = new Config();
        //单机模式  依次设置redis地址和密码
        System.out.println(redisHost);
         config.useSingleServer().setAddress("redis://" + redisHost + ":" + port).setPassword(redisPassword).setDatabase(3);
        // 没有配置redis密码
//        config.useSingleServer().setAddress("redis://" + redisHost + ":" + port);
        return Redisson.create(config);
    }


    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://120.48.127.223:6379").setPassword("3C&wjx@666!").setDatabase(3);
        RedissonClient redissonClient = Redisson.create(config);
        // 这里获取公平锁，遵循先进先出原则，方便测试
        RLock fairLock = redissonClient.getLock("LOCK_TEST_KEY");

        try {
            // 尝试加锁
            // waitTimeout 尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败
            // leaseTime   锁的持有时间,超过这个时间锁会自动失效（值应设置为大于业务处理的时间，确保在锁有效期内业务能处理完）
            boolean lock = fairLock.tryLock(3000,-1, TimeUnit.MILLISECONDS);
//            boolean lock = fairLock.tryLock(3000, 60000, TimeUnit.MILLISECONDS);
            if (lock){
                System.out.println("线程:" + Thread.currentThread().getName() + "获得了锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            System.out.println("线程:" + Thread.currentThread().getName() + "准备释放锁");
            // 注意，无论出现任何情况，都要主动解锁
//            fairLock.unlock();
        }



        try {
            // ->_-> 这里使当前方法占用的线程休息10秒，不要立即结束
            Thread.sleep(10000);

            System.in.read();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

}