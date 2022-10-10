package src.main.java.com.example.stategrid.Mapper;

import com.example.stategrid.entity.test7_entity;
import com.example.stategrid.entity.test8_entity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Class4_Mapper {

    @Select("select * from test7")
    List<com.example.stategrid.entity.test7_entity> findAll_test7();

    @Select("select * from test8")
    List<com.example.stategrid.entity.test8_entity> findAll_test8();

    @Insert("insert into test7(date,day,name,gap) values (#{date},#{day},#{name},#{gap})")
    @Transactional
    void addInfo_test7(com.example.stategrid.entity.test7_entity test7_info);
}
