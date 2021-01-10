package com.wcz.university.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ProjectName: service_edu
 * @ClassName: EduApplication
 * @Auther: wczy
 * @Date: 2020-12-31 12:49
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.wcz.university"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
