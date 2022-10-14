package com.example.stategrid.Controller;

import com.example.stategrid.Mapper.Class2_Mapper;
import com.example.stategrid.common.Page;
import com.example.stategrid.common.Result;
import com.example.stategrid.entity.test4_entity;


import com.sun.deploy.net.URLEncoder;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class Class2_Controller {

    @Resource
    Class2_Mapper test_Mapper;

    //下载
    @GetMapping("/stategrid/companyinfo/basicinfo/import/download")
    public Result result_Download(@RequestParam(defaultValue = "") String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException {
        Map<String, Object> data = new LinkedHashMap<>();
        data=null;
        if (!fileName.equals("")) {
            //设置文件路径
            String realPath = "C:/Users/Administrator/Desktop/stategrid/src/file";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                try{
                    InputStream inputStream = new FileInputStream(file);
                    response.setContentType("application/force-download");// 设置强制下载不打开
                    OutputStream out = response.getOutputStream();
                    response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
                    int b = 0;
                    byte[] buffer = new byte[1000000];
                    while (b != -1) {
                        b = inputStream.read(buffer);
                        if (b != -1) {
                            out.write(buffer, 0, b);
                        }
                    }
                    inputStream.close();
                    out.close();
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            else{
                return Result.error("文件不存在");
            }
        }
        else{
            return Result.error("文件名未名");
        }
    }


    //上传

    @PostMapping("/stategrid/companyinfo/basicinfo/import/upload")
    public Result result_upload(@RequestParam("file") MultipartFile file) throws IOException {
        
        if(file.getOriginalFilename().equals(""))
            return Result.error("Fail to upload!");
        else {
            System.out.println(file.getOriginalFilename());//文件名
            System.out.println(file.getContentType());//文件类型
            System.out.println(file.getSize());//文件大小
            System.out.println(file.getInputStream());//文件的输入流

            //获得文件上传的路径
            String path = ResourceUtils.getURL("classpath:").getPath() + "/static/files";

            System.out.println(path);
            java.io.File newFile = new java.io.File(path);//由于自定义的实体类和java.io.File重名
            //文件夹不存在则重建
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            //上传
            String fileName = file.getOriginalFilename();
            file.transferTo(new java.io.File(newFile, fileName));
            //将文件上传的url存入数据表中
            System.out.println("文件上传成功");
            //        Date date = new Date();
            //        Timestamp time = new Timestamp(date.getTime());//mysql中的日期格式
            //        File file1=new File(fileName,path,time);
            //        fileService.addFile(file1);//调用service方法 将文件信息插入数据库

            //        return "redirect:/file";

            Map<String, Object> data = new LinkedHashMap<>();
            data = null;
            return Result.success(data);
        }
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
