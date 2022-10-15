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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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

    @Override
    public boolean downloadTemplate(String path, HttpServletResponse response) {
        try {
            path="C:\\Users\\Mr.Chestnut\\Desktop\\demo\\src\\main\\java\\suep\\example\\demo\\file\\template.xlsx";
            // path是指想要下载的文件的路径
            File file = new File(path);
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
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
