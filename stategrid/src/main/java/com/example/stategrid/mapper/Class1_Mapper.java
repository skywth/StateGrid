package com.example.stategrid.mapper;

import com.example.stategrid.entity.test1_entity;
import com.example.stategrid.entity.test2_entity;
import com.example.stategrid.entity.test3_entity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Class1_Mapper {

    @Select("select * from test1")
    List<test1_entity> findAll_test1();

    @Select("select * from test2")
    List<test2_entity> findAll_test2();

    @Select("select * from test3 limit #{offset},#{pageSize}")
    List<test3_entity> findByPage_test3(Integer offset, Integer pageSize);

    @Select("select * from test3 where name=#{name} or type=#{type} or state=#{state} or isCompletion=#{isCompletion} limit #{offset},#{pageSize}")
    List<test3_entity> findByRelation_test3(String name,String type,String state,String isCompletion,Integer offset, Integer pageSize);


}
