package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult<Emp> getPage(Integer page, Integer pageSize,String name, Integer gender,
                            LocalDate begin, LocalDate end);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp search(Integer id);
}
