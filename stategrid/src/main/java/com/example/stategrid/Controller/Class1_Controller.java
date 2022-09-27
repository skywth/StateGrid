package com.example.stategrid.Controller;

import com.example.stategrid.Mapper.Class1_Mapper;
import com.example.stategrid.common.Result;
import com.example.stategrid.common.Result_List;
import com.example.stategrid.entity.test3_entity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Class1_Controller {

    @Resource
    Class1_Mapper test1_Mapper;
    @Resource
    Class1_Mapper test2_Mapper;
    @Resource
    Class1_Mapper test3_Mapper;


//    @GetMapping("/stategrid/visualization/realtimedata/statistics/list")
//    public Result_List list() {        //获取表1数据（List）
//
//        return Result_List.success(test1_Mapper.findAll_test1());
//    }
    @GetMapping("/stategrid/visualization/realtimedata/statistics/list")
    public Result list_statistics() {
        Map<String, Object> data = new LinkedHashMap<>();     //data以Map形式
//        data.put("total", test1_Mapper.findAll().get(0).getTotal());
//        data.put("selectedSelect", test1_Mapper.findAll().get(0).getSelectedSelect());
//        data.put("selectedNotStandard",test1_Mapper.findAll().get(0).getSelectedNS());
//        data.put("special", test1_Mapper.findAll().get(0).getSpecial());
//        data.put("estimateSurplus", test1_Mapper.findAll().get(0).getEstimateSurplus());   //依次获值
        data.put("data",test1_Mapper.findAll_test1());
        return Result.success(data);
    }

    @GetMapping("/stategrid/visualization/realtimedata/gap/list")
    public Result_List list_gap(){
        //获取gap数据
        return Result_List.success(test2_Mapper.findAll_test2());
    }

    @GetMapping("/stategrid/visualization/realtimedata/detail/list")
    public Result_List findByPage(@RequestParam(defaultValue = "") String name,@RequestParam(defaultValue = "") String type,@RequestParam(defaultValue = "") String state,@RequestParam(defaultValue = "") String isCompletion,@RequestParam(defaultValue = "1") Integer pageNum,       //查找操作
                                  @RequestParam(defaultValue = "10") Integer pageSize){
        Integer offset = (pageNum - 1) * pageSize;
        if(name.equals("")&&type.equals("")&&state.equals("")&&isCompletion.equals("")) {
            List<test3_entity> test3Data = test3_Mapper.findByPage_test3(offset, pageSize);
            return Result_List.success(test3Data);
        }
        else{
            List<test3_entity> test3Data_rela = test3_Mapper.findByRelation_test3(name,type,state,isCompletion,offset,pageSize);
            return Result_List.success(test3Data_rela);
        }
    }



}
