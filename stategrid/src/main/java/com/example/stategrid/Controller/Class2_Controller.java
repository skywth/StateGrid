package com.example.stategrid.Controller;

import com.example.stategrid.Mapper.Class2_Mapper;
import com.example.stategrid.common.Page;
import com.example.stategrid.common.Result;
import com.example.stategrid.entity.test4_entity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Class2_Controller {

    @Resource
    Class2_Mapper test_Mapper;

    //下载
    @GetMapping("/stategrid/companyinfo/basicinfo/import/download")
    public Result result_Download(){
        Map<String, Object> data = new LinkedHashMap<>();
        data=null;
        return Result.success(data);
    }

    //上传
    @PostMapping("/stategrid/companyinfo/basicinfo/import/upload")
    public Result result_upload(@RequestParam(defaultValue = "") String file_name){
        Map<String, Object> data = new LinkedHashMap<>();
        data=null;
        if(file_name.equals(""))
            return Result.error("Fail to upload!");
        else
            return Result.success(data);
    }


    //通过企业名称查询
    @GetMapping("/stategrid/companyinfo/basicinfo/import/list")
    public Result findByRelation_test4(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String id, @RequestParam(defaultValue = "") String standby1, @RequestParam(defaultValue = "1") Integer pageNum,       //查找操作
                                  @RequestParam(defaultValue = "1") Integer pageSize){
        Integer offset = (pageNum - 1) * pageSize;
        Page page=new Page();
        Map<String, Object> data = new LinkedHashMap<>();
        if(name.equals("") && id.equals("") && standby1.equals("")) {
            Integer total=test_Mapper.countAllTest4();

            page.setTotal(total);
            page.setCurrentPage(pageNum);
            page.setPageCount(total/pageSize);

            List<test4_entity> test4Data = test_Mapper.findByPage_test4(offset, pageSize);
            data.put("pageInfo",page);
            data.put("data",test4Data);
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

    //添加一条数据
    @PostMapping("/stategrid/companyinfo/basicinfo/import/one")
    public Result addInfo_test4(@RequestBody test4_entity test4_info){
        Map<String, Object> data = new LinkedHashMap<>();
        if(test4_info.getName()==null || test4_info.getId()==null ||test4_info.getType()==null||test4_info.getFeature()==null || test4_info.getStandby1()==null || test4_info.getStandby2()==null)
            return Result.error("添加失败！");
        else {
            test_Mapper.addInfo_test4(test4_info);
            return Result.success(data);
        }
    }

    //更新数据
    @PutMapping("/stategrid/companyinfo/basicinfo/import/one")
    public Result updateInfo_test4(@RequestBody test4_entity test4_info) {
        Map<String, Object> data = new LinkedHashMap<>();
        if (test4_info.getName() == null || test4_info.getId() == null || test4_info.getType() == null || test4_info.getFeature() == null || test4_info.getStandby1() == null || test4_info.getStandby2() == null)
            return Result.error("更新失败！");
        else{
            test_Mapper.updateInfo_test4(test4_info);
            return Result.success(data);
        }
    }


    //删除数据
    @DeleteMapping("/stategrid/companyinfo/basicinfo/import/one")
    public Result deleteTest4(@RequestParam("id") String id){
        Map<String, Object> data = new LinkedHashMap<>();
        if(id==null)
            return Result.error("删除失败");
        else{
            test_Mapper.deleteTest4(id);
            return Result.success(data);
        }
    }
}
