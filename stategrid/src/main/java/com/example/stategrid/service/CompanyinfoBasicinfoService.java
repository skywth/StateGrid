package com.example.stategrid.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.stategrid.entity.Company;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Li Ming
 * @e-mail liming.school@qq.com
 * @create 2022-10-15 14:22
 */
public interface CompanyinfoBasicinfoService {
    public boolean downloadTemplate(HttpServletResponse response);

    public Page<Company> getList(int pageNum, int pageSize);

    public Page<Company> getListByCode(String code,int pageNum,int pageSize);

    public Page<Company> getListByName(String name,int pageNum,int pageSize);

    public boolean addOne(Company company);

    public boolean updateOne(Company company);

    public boolean deleteOne(Company company);
}
