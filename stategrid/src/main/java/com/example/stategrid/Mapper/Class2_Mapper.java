package com.example.stategrid.Mapper;

import com.example.stategrid.entity.test3_entity;
import com.example.stategrid.entity.test4_entity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Class2_Mapper {

    @Select("select * from test4 limit #{offset},#{pageSize}")
    List<test4_entity> findByPage_test4(Integer offset, Integer pageSize);

    @Select("select * from test4 where name=#{name} or id=#{id} or standby1=#{standby1} limit #{offset},#{pageSize}")
    List<test4_entity> findByRelation_test4(String name,String id,String standby1,Integer offset, Integer pageSize);

    @Select("select count(id) from test4")
    Integer countAllTest4();

    @Select("select count(id) from test4 where name=#{name} or id=#{id} or standby1=#{standby1}")
    Integer countByRelationTest4(String name,String id,String standby1);
}
