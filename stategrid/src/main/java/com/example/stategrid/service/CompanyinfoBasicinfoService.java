package com.example.stategrid.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.stategrid.entity.Company;
import com.example.stategrid.entity.Notice;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Li Ming
 * @e-mail liming.school@qq.com
 * @create 2022-10-15 14:22
 */
public interface CompanyinfoBasicinfoService {
    public boolean downloadTemplate(String path, HttpServletResponse response);

    public Page<Company> getList(int pageNum, int pageSize);

    public Page<Company> getListByCode(String code,int pageNum,int pageSize);

    public Page<Company> getListByName(String name,int pageNum,int pageSize);

    public boolean addOne(Company company);

    public boolean updateOne(Company company);

    public boolean deleteOne(Company company);
}
