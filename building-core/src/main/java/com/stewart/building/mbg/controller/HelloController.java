package com.stewart.building.mbg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stewart
 * @create 2021/11/21
 */
@Api(tags = "测试权限模块")
@RestController
public class HelloController {

    @ApiOperation(value = "测试")
    @GetMapping("/demo")
    public String hello(){
        return "hello";
    }

    @GetMapping("/role/basic/ad")
    public String hello2(){
        return "/employee/basic/hello";
    }

    @GetMapping("/role/advanced")
    public String hello3(Integer id){
        return "/employee/basic/hello2";
    }

}
