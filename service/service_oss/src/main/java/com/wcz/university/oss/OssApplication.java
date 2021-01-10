package com.wcz.university.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ProjectName: service_edu
 * @ClassName: OssApplication
 * @Auther: wczy
 * @Date: 2021-01-03 19:19
 * @Version 1.0
 **/
@ComponentScan(basePackages = {"com.wcz.university"})
//springboot启动时会自动去找数据库配置，oss服务不需要操作数据库，所以让服务启动时不去加载数据库配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
