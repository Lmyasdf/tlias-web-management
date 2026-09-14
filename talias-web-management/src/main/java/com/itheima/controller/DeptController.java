package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    public Result findAll(){
        List<Dept> list = deptService.findAll();
        return Result.success(list);
    }

    //删除部门
    public Result delete(Integer id){
        deptService.delete(id);
        return Result.success();
    }

    //添加部门
    public Result insert(@RequestBody Dept dept){
        System.out.println("添加部门" + dept);
        deptService.insert(dept);
        return Result.success();
    }

    //查询部门
    @GetMapping("{id}")
    public Result getInfo(Integer id){
        Dept dept = new Dept();
        dept = deptService.search(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping("/setData")
    public Result setData(@RequestBody Dept dept){
        deptService.set(dept);
        return Result.success();
    }

}
