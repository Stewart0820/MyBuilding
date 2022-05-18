package com.stewart.building;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Stewart
 */
@SpringBootApplication
@MapperScan("com.stewart.building.mbg.mapper")
public class BuildingCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildingCoreApplication.class, args);
    }

}
