package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result; 
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j//日志
@RestController
@RequestMapping("/depts")
public class DeptController {
    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result findAll(){
        log.info("查询全部部门数据");
        List<Dept> list = deptService.findAll();
        return Result.success(list);
    }

    //删除部门
    @DeleteMapping
    public Result delete(Integer id){
        deptService.delete(id);
        return Result.success();
    }

    //添加部门
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
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
        log.info("修改部门：{}",dept);
        deptService.set(dept);
        return Result.success();
    }

}
