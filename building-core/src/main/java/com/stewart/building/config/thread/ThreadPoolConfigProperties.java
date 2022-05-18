package com.stewart.building.config.thread;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Stewart
 * @create 2022/4/22
 * @Description
 */
@ConfigurationProperties(prefix = "building.thread")
@Component
public class ThreadPoolConfigProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
