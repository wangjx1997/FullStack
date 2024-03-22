package org.wjx.properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class LogConfig implements CommandLineRunner {

    @Autowired
    YmlProperties ymlProperties;

    @Resource
    ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info("ymalConfig 日志打印配置 : {}", objectMapper.writeValueAsString(ymlProperties));
    }
}