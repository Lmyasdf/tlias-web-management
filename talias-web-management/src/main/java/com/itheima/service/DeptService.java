package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void delete(Integer id);

    void insert(Dept dept);

    Dept search(Integer deptId);

    void set(Dept dept);
}
