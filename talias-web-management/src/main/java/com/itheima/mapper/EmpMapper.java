package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.JobOption;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
/*    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();

    @Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);*/

    public List<Emp>list(String name, Integer gender, LocalDate begin,LocalDate end);

    //主键返回：获取生成的主键存到对象里
    @Options(useGeneratedKeys = true,keyProperty="id")
    @Insert("insert into emp(username,password,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) values (#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    void delete(List<Integer> ids);


    Emp search(Integer id);

    void update(Emp emp);

    List<Map<String,Object>> getEmpJobData();
}