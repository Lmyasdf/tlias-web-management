package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.selectAll();
    }

    //删除
    @Override
    public void delete(Integer id){
        deptMapper.deleteById(id);
    }

    //插入
    @Override
    public void insert(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    //查询
    @Override
    public Dept search(Integer deptId){
        return deptMapper.search(deptId);
    }

    //修改
    @Override
    public void set(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.set(dept);
    }
}

