package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    // 查询所有部门
    @Select("select * from dept order by update_time desc")
    List<Dept> selectAll();

    //删除
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //插入
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime}) ")
    void insert(Dept dept);

    //查询
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept search(Integer id);

    //修改
    @Update("update dept set name=#{name}, update_time = #{updateTime} where id = #{id}")
    void set(Dept dept);
}
