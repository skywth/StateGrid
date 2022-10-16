package com.example.stategrid.controller;


import com.example.stategrid.entity.*;
import com.example.stategrid.mapper.Class3_Mapper;
import com.example.stategrid.common.Page;
import com.example.stategrid.common.Result;
import com.example.stategrid.common.Result_List;
import com.example.stategrid.utils.UUIDUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/stategridinfo/orderuse")
public class Class3_Controller {


    @Resource
    Class3_Mapper test_Mapper;

    //查询（有序用电）
    @GetMapping("candidateinfo/list")
    public Result findByRelation_test5(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0") long id, @RequestParam(defaultValue = "") String start_date, @RequestParam(defaultValue = "") String end_date, @RequestParam(defaultValue = "1") Integer pageNum,       //查找操作
                                       @RequestParam(defaultValue = "1") Integer pageSize){

        Integer offset = (pageNum - 1) * pageSize;
        Page page=new Page();
        Map<String, Object> data = new LinkedHashMap<>();
        if (start_date.equals("") || end_date.equals("")){
            return Result.error("请输入日期！");
        }
        else {
            if (name.equals("") && id==0) {
                Integer total = test_Mapper.countAllCompany(start_date,end_date);
                page.setTotal(total);
                page.setCurrentPage(pageNum);
                page.setPageCount(total / pageSize);
                List<Company> test5Data = test_Mapper.findByPage_company(start_date,end_date,offset, pageSize);

                data.put("pageInfo", page);
                data.put("data", test5Data);
                return Result.success(data);
            } else {
                Integer total = test_Mapper.countByRelationCompany(start_date,end_date,name, id);
                page.setTotal(total);
                page.setCurrentPage(pageNum);
                page.setPageCount(total / pageSize);
                List<Company> test5Data_rela = test_Mapper.findByRelation_Company(start_date,end_date,name, id, offset, pageSize);
                data.put("pageInfo", page);
                data.put("data", test5Data_rela);
                return Result.success(data);
            }
        }
    }

    //提交候选企业
    @PostMapping("candidateinfo/list")
    public Result addInfo_candidate(@RequestBody Candidate candidate){
        Map<String, Object> data = new LinkedHashMap<>();
        if(candidate.getEndDate()==null ||candidate.getStartDate()==null || candidate.getCompanyId()==0 || candidate.getQuota()==0)
            return Result.error("添加失败！");
        else {
            candidate.setUuid(UUIDUtil.getUUID32());
            test_Mapper.addInfo_candidate(candidate);
            return Result.success(data);
        }
    }

    //提交计划（提交缺口信息）电力缺口添加数据接口
    @PostMapping("gap/list")
    public Result addGap(@RequestBody Gap gap){
        Map<String, Object> data = new LinkedHashMap<>();
        if(gap.getDate()==null || gap.getPlan_gap()==null || gap.getGap()==null)
            return Result.error("请补充完整信息！");
        else {
            gap.setUuid(UUIDUtil.getUUID32());
            test_Mapper.addInfo_gap(gap);
            return Result.success(data);
        }
    }

    //电力缺口修改数据接口
    @PutMapping("gap/list")
    public Result updateGap(@RequestBody Gap gap){
        Map<String, Object> data = new LinkedHashMap<>();
        if(gap.getId()==null || gap.getDate()==null || gap.getPlan_gap()==null || gap.getGap()==null)
            return Result.error("请完善信息");
        else{
            test_Mapper.updateGap(gap);
            return Result.success(data);
        }

    }

    //缺口信息查询
    @GetMapping("gap/list")
    public Result_List findByDate_test6(@RequestParam(defaultValue = "")String start_date,@RequestParam(defaultValue = "")String end_date){
        if(start_date.equals("") || end_date.equals(""))
            return Result_List.error("请输入查询起止时间！");
        else{
            List<test6_entity> Gap_info=test_Mapper.findByDate_gap(start_date,end_date);
            return Result_List.success(Gap_info);
        }
    }

}
