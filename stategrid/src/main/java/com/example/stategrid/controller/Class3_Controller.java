package com.example.stategrid.controller;


import com.example.stategrid.entity.*;
import com.example.stategrid.mapper.Class3_Mapper;
import com.example.stategrid.common.Page;
import com.example.stategrid.common.Result;
import com.example.stategrid.common.Result_List;
import com.example.stategrid.utils.UUIDUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public Result findByRelation_test5(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String companyCode, @RequestParam(defaultValue = "") String start_date, @RequestParam(defaultValue = "") String end_date, @RequestParam(defaultValue = "1") Integer pageNum,       //查找操作
                                       @RequestParam(defaultValue = "1") Integer pageSize){

        Integer offset = (pageNum - 1) * pageSize;
        Page page=new Page();
        Map<String, Object> data = new LinkedHashMap<>();
        if (start_date.equals("") || end_date.equals("")){
            return Result.error("请输入日期！");
        }
        else {
            if (name.equals("") && companyCode.equals("")) {
                Integer total = test_Mapper.countAllCompany(start_date,end_date);
                page.setTotal(total);
                page.setCurrentPage(pageNum);
                page.setPageCount((total / pageSize)+1);
                List<Company> CompanyData = test_Mapper.findByPage_company(start_date,end_date,offset, pageSize);
                List<Candidate> CandidateData=test_Mapper.findInfo_candidate(start_date,end_date);
                List<CompanyAllInfo_entity> CompanyAllInfo = new ArrayList<>();
                for(int i=0;i<CompanyData.size();i++) {
                    CompanyAllInfo_entity CAI_entity=new CompanyAllInfo_entity();
                    CAI_entity.setId(CandidateData.get(i).getCompanyId());
                    CAI_entity.setName(CompanyData.get(i).getName());
                    CAI_entity.setType(CompanyData.get(i).getType());
                    CAI_entity.setBaseline(CompanyData.get(i).getBaseline());
                    CAI_entity.setCheck("ture");
                    CAI_entity.setQuotaValue(CandidateData.get(i).getQuota());
                    double percent=100.0*(1.0*CandidateData.get(i).getQuota()/CompanyData.get(i).getBaseline());
                    CAI_entity.setQuotaPercentage((int)percent);
                    CompanyAllInfo.add(i,CAI_entity);
                }
                data.put("pageInfo", page);
                data.put("data", CompanyAllInfo);
                return Result.success(data);
            } else {
                Integer total = test_Mapper.countByRelationCompany(start_date,end_date,name, companyCode);
                page.setTotal(total);
                page.setCurrentPage(pageNum);
                page.setPageCount((total / pageSize)+1);
                List<Company> CompanyData_rela = test_Mapper.findByRelation_Company(start_date,end_date,name, companyCode, offset, pageSize);
                List<Candidate> CandidateData_rela=test_Mapper.findRelationInfo_candidate(start_date,end_date,name,companyCode);
                List<CompanyAllInfo_entity> CompanyRelationInfo = new ArrayList<>();
                for(int i=0;i<CandidateData_rela.size();i++) {
                    CompanyAllInfo_entity CAI_entity_r=new CompanyAllInfo_entity();
                    CAI_entity_r.setId(CandidateData_rela.get(i).getCompanyId());
                    CAI_entity_r.setName(CompanyData_rela.get(i).getName());
                    CAI_entity_r.setType(CompanyData_rela.get(i).getType());
                    CAI_entity_r.setBaseline(CompanyData_rela.get(i).getBaseline());
                    CAI_entity_r.setCheck("ture");
                    CAI_entity_r.setQuotaValue(CandidateData_rela.get(i).getQuota());
                    double percent_r=100.0*(1.0*CandidateData_rela.get(i).getQuota()/CompanyData_rela.get(i).getBaseline());
                    CAI_entity_r.setQuotaPercentage((int)percent_r);
                    CompanyRelationInfo.add(i,CAI_entity_r);
                }
                data.put("pageInfo", page);
                data.put("data", CompanyRelationInfo);
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
    public Result addGap(@RequestBody List<Gap> gap){
        Map<String, Object> data = new LinkedHashMap<>();
        if(gap.get(0).getDate()==null || gap.get(0).getPlanGap()==0 || gap.get(0).getGap()==0)
            return Result.error("请补充完整信息！");
        else {
            for(int i=0;i<gap.size();i++) {
                gap.get(i).setUuid(UUIDUtil.getUUID32());
                test_Mapper.addInfo_gap(gap.get(i));
            }
            return Result.success(data);
        }
    }

    //电力缺口修改数据接口
    @PutMapping("gap/list")
    public Result updateGap(@RequestBody List<Gap> gap){
        Map<String, Object> data = new LinkedHashMap<>();
        if(gap.get(0).getId()==0 || gap.get(0).getDate()==null || gap.get(0).getPlanGap()==0 || gap.get(0).getGap()==0)
            return Result.error("请完善信息");
        else{
            for(int i=0;i<gap.size();i++)
                test_Mapper.updateGap(gap.get(i));
            return Result.success(data);
        }

    }

    //缺口信息查询
    @GetMapping("gap/list")
    public Result_List findByDate_test6(@RequestParam(defaultValue = "")String start_date,@RequestParam(defaultValue = "")String end_date){
        if(start_date.equals("") || end_date.equals(""))
            return Result_List.error("请输入查询起止时间！");
        else{
            List<Gap> Gap_info=test_Mapper.findByDate_gap(start_date,end_date);
            return Result_List.success(Gap_info);
        }
    }

}
