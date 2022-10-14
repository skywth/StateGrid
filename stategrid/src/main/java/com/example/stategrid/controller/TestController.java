package com.example.stategrid.controller;

import com.example.stategrid.entity.Notice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author Li Ming
 * @e-mail liming.school@qq.com
 * @create 2022-10-15 1:06
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public Notice getOne(){
        return new Notice(1,"xx",new Date(),"2","2","2","2",1);
    }
}
