package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    //分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
                       ) {
        log.info("分页查询:{},{},{},{},{},{}", page, pageSize,name,gender,begin,end);
        PageResult<Emp> pageResult = empService.getPage(page,pageSize,name,gender,begin,end);
        return Result.success(pageResult);
    }

//    新增员工
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }

//    删除员工
    @DeleteMapping
    public Result delete(@RequestParam List<Integer>ids){
        log.info("删除员工的id：{}",ids);
        empService.delete(ids);
        return Result.success();
    }

//    查询员工
    @GetMapping("/{id}")
    public Result search(@PathVariable Integer id){
        log.info("查询id为：{}",id);
        Emp emp = empService.search(id);
        return Result.success(emp);
    }

//    修改员工数据
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工: {}",emp);
        empService.update(emp);
        return Result.success();
    }
}