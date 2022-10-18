package com.example.stategrid.mapper;

import com.example.stategrid.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Class3_Mapper {

    @Select("select * from candidate where start_date between #{start_date} and #{end_date} and end_date between #{start_date} and #{end_date}")
    List<Candidate> findInfo_candidate(String start_date,String end_date);

    @Select("select * from candidate where start_date between #{start_date} and #{end_date} and end_date between #{start_date} and #{end_date} and (id=#{id} or id=(select id from company where name=#{name}))")
    List<Candidate> findRelationInfo_candidate(String start_date,String end_date,String name,long id);



    @Select("select count(id) from company where id in (select company_id from candidate where start_date between #{start_date} and #{end_date} and end_date between #{start_date} and #{end_date})")
    Integer countAllCompany(String start_date,String end_date);

    @Select("select * from company where id in (select company_id from candidate where start_date between #{start_date} and #{end_date} and end_date between #{start_date} and #{end_date}) limit #{offset},#{pageSize}")
    List<Company> findByPage_company(String start_date, String end_date, Integer offset, Integer pageSize);

    @Select("select count(id) from company where id in (select company_id from candidate where start_date between #{start_date} and #{end_date} and end_date between #{start_date} and #{end_date}) and (name=#{name} or id=#{id})")
    Integer countByRelationCompany(String start_date,String end_date,String name,long id);

    @Select("select * from company where id in (select company_id from candidate where start_date between #{start_date} and #{end_date} and end_date between #{start_date} and #{end_date}) and (name=#{name} or id=#{id}) limit #{offset},#{pageSize}")
    List<Company> findByRelation_Company(String start_date,String end_date, String name, long id, Integer offset, Integer pageSize);


    @Insert("insert into candidate(uuid,end_date,start_date,company_id,quota) values (#{uuid},#{endDate},#{startDate},#{companyId},#{quota})")
    @Transactional
    void addInfo_candidate(Candidate candidate);


    @Insert("insert into gap(uuid,date,plan_gap,gap) values (#{uuid},#{date},#{planGap},#{gap})")
    @Transactional
    void addInfo_gap(Gap gap);

    @Update("update gap set date=#{date},plan_gap=#{planGap},gap=#{gap} where id=#{id}")
    @Transactional
    void updateGap(Gap gap);


    @Select("select * from gap where date between #{start_date} and #{end_date}")
    List<Gap> findByDate_gap(String start_date,String end_date);

}
