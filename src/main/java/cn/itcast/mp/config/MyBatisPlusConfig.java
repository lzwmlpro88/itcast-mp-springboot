package cn.itcast.mp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: longzhangwei
 * @create: 2020-12-03-1:14
 */
@Configuration
//@MapperScan("cn.itcast.mp.mapper")Springboot启动类上配置了，这里就不用配置了
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
