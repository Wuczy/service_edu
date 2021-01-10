package com.wcz.university.eduservice.controller;

import com.wcz.university.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: service_edu
 * @ClassName: EduLoginController
 * @Auther: wczy
 * @Date: 2021-01-01 17:27
 * @Version 1.0  登陆控制器
 **/
@CrossOrigin//解决跨域
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    /**
    * @Author Wczy
    * @Date 2021-01-01 17:31
    * @Param []
    * @Return com.wcz.university.utils.Result
    * @description 登陆
    **/
    @PostMapping("login")
    public Result login(){
        return Result.ok()
                .data("token","admin");
    }

    /**
    * @Author Wczy
    * @Date 2021-01-01 17:37
    * @Param []
    * @Return com.wcz.university.utils.Result
    * @description 获取用户信息
    **/
    @GetMapping("info")
    public Result info(){
        return Result.ok()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
