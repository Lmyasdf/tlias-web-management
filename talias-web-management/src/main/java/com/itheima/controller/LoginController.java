package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    private Result login(@RequestBody Emp emp) {
        log.info("用户登录：{}",emp.getUsername());
        User data = empService.login(emp);
        if(data != null) return Result.success(data);
        return Result.error("用户名或密码错误");
    }
}
