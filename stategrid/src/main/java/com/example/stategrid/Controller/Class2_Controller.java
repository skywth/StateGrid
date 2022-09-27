package com.example.stategrid.Controller;


import com.example.stategrid.Mapper.Class2_Mapper;
import com.example.stategrid.common.Page;
import com.example.stategrid.common.Result;
import com.example.stategrid.entity.test4_entity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Class2_Controller {

    @Resource
    Class2_Mapper test_Mapper;

    @GetMapping("/stategrid/companyinfo/basicinfo/import/download")
    public Result result_Download(){
        Map<String, Object> data = new LinkedHashMap<>();
        data=null;
        return Result.success(data);
    }

    @PostMapping("/stategrid/companyinfo/basicinfo/import/upload")
    public Result result_upload(@RequestParam(defaultValue = "") String file_name){
        Map<String, Object> data = new LinkedHashMap<>();
        data=null;
        if(file_name.equals(""))
            return Result.error("Fail to upload!");
        else
            return Result.success(data);
    }

    @GetMapping("/stategrid/companyinfo/basicinfo/import/list")
    public Result findByPage(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String id, @RequestParam(defaultValue = "") String standby1, @RequestParam(defaultValue = "2") Integer pageNum,       //查找操作
                                  @RequestParam(defaultValue = "1") Integer pageSize){
        Integer offset = (pageNum - 1) * pageSize;
        Page page=new Page();
        Map<String, Object> data = new LinkedHashMap<>();
        if(name.equals("") && id.equals("") && standby1.equals("")) {
            Integer total=test_Mapper.countAllTest4();
            page.setTotal(total);
            page.setCurrentPage(pageNum);
            page.setPageCount(total/pageSize);


            List<test4_entity> test3Data = test_Mapper.findByPage_test4(offset, pageSize);
            data.put("pageInfo",page);
            data.put("data",test3Data);
            return Result.success(data);
        }
        else{
            Integer total=test_Mapper.countByRelationTest4(name,id,standby1);
            page.setTotal(total);
            page.setCurrentPage(pageNum);
            page.setPageCount(total/pageSize);
            List<test4_entity> test4Data_rela = test_Mapper.findByRelation_test4(name,id,standby1,offset,pageSize);
            data.put("pageInfo",page);
            data.put("data",test4Data_rela);
            return Result.success(data);
        }
    }










}
