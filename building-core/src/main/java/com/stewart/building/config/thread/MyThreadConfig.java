package com.stewart.building.config.thread;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Stewart
 *
 * @create 2022/4/22
 * @Description
 */
@Configuration
public class MyThreadConfig {
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(20, 200, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
