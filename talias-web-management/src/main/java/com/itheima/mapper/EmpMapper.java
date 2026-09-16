package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
/*    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();

    @Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);*/

    public List<Emp>list(String name, Integer gender, LocalDate begin,LocalDate end);
}
