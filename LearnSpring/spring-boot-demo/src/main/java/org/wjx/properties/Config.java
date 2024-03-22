package org.wjx.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangJX
 * @Date 2024/3/22 11:09
 * @Description
 */
@Configuration
@EnableConfigurationProperties(YmlProperties.class)
public class Config {
}
