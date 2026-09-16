package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;

public interface EmpService {
    PageResult<Emp> getPage(Integer page, Integer pageSize);
}
