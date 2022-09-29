package com.example.stategrid.Controller;


import com.example.stategrid.Mapper.Class3_Mapper;
import com.example.stategrid.common.Page;
import com.example.stategrid.common.Result;
import com.example.stategrid.common.Result_List;
import com.example.stategrid.entity.test5_entity;
import com.example.stategrid.entity.test6_entity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Class3_Controller {


    @Resource
    Class3_Mapper test_Mapper;

    //查询（有序用电）
    @GetMapping("/stategrid/stategridinfo/orderuse/candidateinfo/list")
    public Result findByRelation_test5(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String id, @RequestParam(defaultValue = "") String dateRange,@RequestParam(defaultValue = "1") Integer pageNum,       //查找操作
                                       @RequestParam(defaultValue = "1") Integer pageSize){

        Integer offset = (pageNum - 1) * pageSize;
        Page page=new Page();
        Map<String, Object> data = new LinkedHashMap<>();
        if (dateRange.equals("")){
            return Result.error("请输入日期！");
        }
        else {
            if (name.equals("") && id.equals("")) {
                Integer total = test_Mapper.countAllTest5(dateRange);
                page.setTotal(total);
                page.setCurrentPage(pageNum);
                page.setPageCount(total / pageSize);
                List<test5_entity> test5Data = test_Mapper.findByPage_test5(dateRange,offset, pageSize);

                data.put("pageInfo", page);
                data.put("data", test5Data);
                return Result.success(data);
            } else {
                Integer total = test_Mapper.countByRelationTest5(dateRange,name, id);
                page.setTotal(total);
                page.setCurrentPage(pageNum);
                page.setPageCount(total / pageSize);
                List<test5_entity> test5Data_rela = test_Mapper.findByRelation_test5(dateRange,name, id, offset, pageSize);
                data.put("pageInfo", page);
                data.put("data", test5Data_rela);
                return Result.success(data);
            }
        }
    }

    //提交候选企业
    @PostMapping("/stategrid/stategridinfo/orderuse/candidateinfo/list")
    public Result addInfo_test5(@RequestBody test5_entity test5_info){
        Map<String, Object> data = new LinkedHashMap<>();
        if(test5_info.getDateRange()==null ||test5_info.getName()==null || test5_info.getId()==null ||test5_info.getType()==null || test5_info.getQuotaValue()==null || test5_info.getQuotaPercentage() ==null)
            return Result.error("添加失败！");
        else {
            test_Mapper.addInfo_test5(test5_info);
            return Result.success(data);
        }
    }

    //提交计划（提交缺口信息）
    @PostMapping("/stategrid/stategridinfo/orderuse/gap/list")
    public Result addGap_test6(@RequestBody test6_entity test6_info){
        Map<String, Object> data = new LinkedHashMap<>();
        if(test6_info.getDate()==null || test6_info.getGap()==null)
            return Result.error("请补充完整信息！");
        else {
            test_Mapper.addInfo_test6(test6_info);
            return Result.success(data);
        }
    }

    //缺口信息查询
    @GetMapping("/stategrid/stategridinfo/orderuse/gap/list")
    public Result_List findByDate_test6(@RequestParam(defaultValue = "")String date){
        if(date.equals(""))
            return Result_List.error("请输入查询时间！");
        else{
            List<test6_entity> test6_date_info=test_Mapper.findByDate_test6(date);
            return Result_List.success(test6_date_info);
        }
    }

}
