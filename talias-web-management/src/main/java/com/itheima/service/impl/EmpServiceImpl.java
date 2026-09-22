package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.User;
import com.itheima.service.EmpService;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.jdbc.AbstractSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    /*@Override
    public PageResult<Emp> getPage(Integer page,Integer pageSize){
        PageResult<Emp> pr = new PageResult();
        pr.setTotal(empMapper.count());
        pr.setRows(empMapper.list((page-1)*pageSize,pageSize));
        return pr;
    }*/

    @Override
    public PageResult<Emp> getPage(Integer page, Integer pageSize, String name, Integer gender,
                                   LocalDate begin, LocalDate end){
        //设置分页参数
        PageHelper.startPage(page,pageSize);
        //执行查询
        List<Emp>empList = empMapper.list(name,gender,begin,end);

        Page<Emp> p = (Page<Emp>)empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})//事务管理
    @Override
    public void save(Emp emp){
    //    保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setPassword("123456");  // 默认初始密码
        empMapper.insert(emp);


    //    保存员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
         if(!CollectionUtils.isEmpty(exprList)){
             //遍历集合，为empId来赋值
             exprList.forEach(empExpr -> {
                 empExpr.setEmpId(emp.getId());
             });
             empExprMapper.insertBatch(exprList);
         }
    }

//    删除
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids){
        empMapper.delete(ids);
        empExprMapper.delete(ids);
    }

//    查询
    @Override
    public Emp search(Integer id){
        return empMapper.search(id);
    }

//    修改
    @Transactional(rollbackFor = {Exception.class})//多数据库操作要事务管理
    @Override
    public void update(Emp emp){
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        //先删除现有员工工作经历
        empExprMapper.delete(Arrays.asList(emp.getId()));

        //再为新的员工属性赋员工id值
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            //新增工作经历
            empExprMapper.insertBatch(exprList);
        }
    }

//    登录验证
    @Override
    public User login(Emp emp){
        return empMapper.login(emp);
    }
}
