package com.example.stategrid.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.stategrid.common.CommonPage;
import com.example.stategrid.common.CommonResult;
import com.example.stategrid.entity.Company;
import com.example.stategrid.entity.Notice;
import com.example.stategrid.service.CompanyinfoBasicinfoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * @author Li Ming
 * @e-mail liming.school@qq.com
 * @create 2022-10-15 1:06
 */
@CrossOrigin
@RestController
@RequestMapping("/companyinfo/basicinfo")
public class CompanyinfoBasicinfoController {

    @Resource
    private CompanyinfoBasicinfoService companyinfoBasicinfoService;

    @PostMapping("/import/upload")
    public CommonResult upload(MultipartFile file){
//        今天先写到这
        return null;
    }

    /**
     * @param response
     * @功能描述 下载文件:
     */
    @GetMapping("/import/download")
    public CommonResult download(HttpServletResponse response) {
        boolean b = companyinfoBasicinfoService.downloadTemplate(response);
        if (b){
            return null;
        }else {
            return CommonResult.error("下载失败");
        }
    }

    /**
     * 查询符合条件的数据
     * @return
     */
    @GetMapping("/import/list")
    public CommonResult selectList(@RequestParam(required = false) String code,@RequestParam(required = false) String name,int pageNum,int pageSize){
        Page<Company> result =null;
        if (null==code && null==name){
            result = companyinfoBasicinfoService.getList(pageNum, pageSize);
        }
        if (null!=code && null!=name){
            return CommonResult.error("输入参数错误");
        }
//        通过code查询
        if (null!=code){
            result = companyinfoBasicinfoService.getListByCode(code, pageNum, pageSize);
        }
//        通过name查询
        if (null!=name){
            result = companyinfoBasicinfoService.getListByName(name, pageNum, pageSize);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageInfo",new CommonPage(result.getCurrent(),result.getPages(),result.getTotal(),result.getSize()));
        map.put("data",result.getRecords());
        return CommonResult.success(map);
    }

    /**
     * 添加一条数据
     * @param company
     * @return
     */
    @PostMapping("/import/one")
    public CommonResult addOne(Company company){
        if (null==company.getCompanyCode()||null==company.getName()||null==company.getType()||0>=company.getBaseline()||-1==company.getContinuity()){
            return CommonResult.error("输入参数错误");
        }else {
            boolean b = companyinfoBasicinfoService.addOne(company);
            if (b){
                return CommonResult.success(null);
            }else {
                return CommonResult.error("数据库问题");
            }
        }
    }

    /**
     * 修改一条数据
     * @param company
     * @return
     */
    @PutMapping("/import/one")
    public CommonResult updateOne(Company company){
        if (-1==company.getId()||null==company.getCompanyCode()||null==company.getName()||null==company.getType()||0>=company.getBaseline()||-1==company.getContinuity()){
            return CommonResult.error("输入参数错误");
        }else {
            boolean b = companyinfoBasicinfoService.updateOne(company);
            if (b){
                return CommonResult.success(null);
            }else {
                return CommonResult.error("数据库问题");
            }
        }
    }

    /**
     * 删除一条数据
     * @return
     */
    @DeleteMapping("/import/one")
    public CommonResult deleteOne(Company company){
        if (-1==company.getId()){
            return CommonResult.error("输入参数错误");
        }else {
            boolean b = companyinfoBasicinfoService.deleteOne(company);
            if (b){
                return CommonResult.success(null);
            }else {
                return CommonResult.error("数据库问题");
            }
        }
    }
}
