package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

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
}
