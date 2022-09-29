package com.example.stategrid.Mapper;

import com.example.stategrid.entity.test5_entity;
import com.example.stategrid.entity.test6_entity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Class3_Mapper {

    @Select("select count(id) from test5 where dateRange=#{dateRange}")
    Integer countAllTest5(String dateRange);

    @Select("select * from test5 where dateRange=#{dateRange} limit #{offset},#{pageSize}")
    List<test5_entity> findByPage_test5(String dateRange,Integer offset, Integer pageSize);

    @Select("select count(id) from test5 where dateRange=#{dateRange} and (name=#{name} or id=#{id})")
    Integer countByRelationTest5(String dateRange,String name,String id);

    @Select("select * from test5 where dateRange=#{dateRange} and (name=#{name} or id=#{id}) limit #{offset},#{pageSize}")
    List<test5_entity> findByRelation_test5(String dateRange, String name, String id, Integer offset, Integer pageSize);


    @Insert("insert into test5(dateRange,id,name,type,quotaValue,quotaPercentage) values (#{dateRange},#{id},#{name},#{type},#{quotaValue},#{quotaPercentage})")
    @Transactional
    void addInfo_test5(test5_entity test5_info);


    @Insert("insert into test6 values (#{date},#{gap})")
    @Transactional
    void addInfo_test6(test6_entity test6_info);

    @Select("select * from test6 where date=#{date}")
    List<test6_entity> findByDate_test6(String date);

}
