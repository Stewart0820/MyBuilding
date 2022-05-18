package com.stewart.building.config.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Stewart
 * @create 2022/5/4
 * @Description
 */
@Configuration
public class CorsConfig {
    //    @Bean
//    public CorsFilter corsFilter() {
//        //1. 添加 CORS配置信息
//        CorsConfiguration config = new CorsConfiguration();
//        //放行哪些原始域
//        config.addAllowedOrigin("*");
//        //是否发送 Cookie
//        config.setAllowCredentials(true);
//        //放行哪些请求方式
//        config.addAllowedMethod("*");
//        //放行哪些原始请求头部信息
//        config.addAllowedHeader("*");
//        //暴露哪些头部信息
//        config.addExposedHeader("*");
//        //2. 添加映射路径
//        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        corsConfigurationSource.registerCorsConfiguration("/**",config);
//        //3. 返回新的CorsFilter
//        return new CorsFilter(corsConfigurationSource);
//    }
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许cookies跨域
        config.addAllowedOrigin("*");// 允许向该服务器提交请求的URI，*表示全部允许。。这里尽量限制来源域，比如http://xxxx:8080 ,以降低安全风险。。
        config.addAllowedHeader("*");// 允许访问的头信息,*表示全部
        config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.addAllowedMethod("*");// 允许提交请求的方法，*表示全部允许，也可以单独设置GET、PUT等
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
