package com.example.stategrid.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.stategrid.entity.Account;
import com.example.stategrid.entity.Company;
import com.example.stategrid.entity.Notice;
import com.example.stategrid.entity.User;
import com.example.stategrid.mapper.AccountMapper;
import com.example.stategrid.mapper.CompanyMapper;
import com.example.stategrid.mapper.NoticeMapper;
import com.example.stategrid.mapper.UserMapper;
import com.example.stategrid.service.CompanyinfoBasicinfoService;
import com.example.stategrid.utils.UUIDUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Li Ming
 * @e-mail liming.school@qq.com
 * @create 2022-10-15 14:23
 */
@Service
public class CompanyinfoBasicinfoServiceImpl implements CompanyinfoBasicinfoService {
    @Resource
    private CompanyMapper companyMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ResourceLoader resourceLoader;

    @Override
    public boolean downloadTemplate(HttpServletResponse response) {
        String path="excel/template.xlsx";
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            String filename = "模板.xls";
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:" + path);

            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

            inputStream = resource.getInputStream();
            servletOutputStream = response.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                // jvm的垃圾回收
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Page<Company> getList(int pageNum, int pageSize) {
        Page<Company> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted",0);
        Page<Company> result = companyMapper.selectPage(page, queryWrapper);
        return result;
    }

    @Override
    public Page<Company> getListByCode(String code,int pageNum,int pageSize) {
        Page<Company> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_code",code);
        queryWrapper.eq("deleted",0);
        Page<Company> result = companyMapper.selectPage(page, queryWrapper);
        return result;
    }

    @Override
    public Page<Company> getListByName(String name,int pageNum,int pageSize) {
        Page<Company> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        queryWrapper.eq("deleted",0);
        Page<Company> result = companyMapper.selectPage(page, queryWrapper);
        return result;
    }

    @Override
    public boolean addOne(Company company) {
//        设置为0，让数据库自行自增
        company.setId(0);
        company.setUuid(UUIDUtil.getUUID32());
//        查询公司对应的账号id
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_code", company.getCompanyCode());
        User user = userMapper.selectOne(queryWrapper);

        QueryWrapper<Account> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id",user.getId());
        Account account = accountMapper.selectOne(queryWrapper1);
        company.setAccountId(account.getId());

        company.setDeleted(0);
//        插入数据库
        int insert = companyMapper.insert(company);

        return insert==0?false:true;
    }

    @Override
    public boolean updateOne(Company company) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", company.getId());
        updateWrapper.set("company_code", company.getCompanyCode());
        updateWrapper.set("name",  company.getName());
        updateWrapper.set("type",  company.getType());
        updateWrapper.set("baseline",  company.getBaseline());
        updateWrapper.set("continuity",  company.getContinuity());
        int update = companyMapper.update(null, updateWrapper);

        return update==0?false:true;
    }

    @Override
    public boolean deleteOne(Company company) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", company.getId());
        updateWrapper.set("deleted", 1);
        int delete = companyMapper.update(null, updateWrapper);

        return delete==0?false:true;
    }
}
