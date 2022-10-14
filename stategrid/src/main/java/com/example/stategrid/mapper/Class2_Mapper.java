package com.example.stategrid.mapper;

import com.example.stategrid.entity.test4_entity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

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

    @Insert("insert into test4(id,name,type,baseline,feature,standby1,standby2) values (#{id},#{name},#{type},#{baseline},#{feature},#{standby1},#{standby2})")
    @Transactional
    void addInfo_test4(test4_entity test4_info);

    @Update("update test4 set name=#{name},type=#{type},baseline=#{baseline},feature=#{feature},standby1=#{standby1},standby2=#{standby2} where id=#{id}")
    @Transactional
    void updateInfo_test4(test4_entity test4_info);

    @Delete("delete from test4 where id=#{id}")
    @Transactional
    void deleteTest4(String id);

}
