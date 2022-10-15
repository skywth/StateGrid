package com.example.stategrid.utils;

import java.util.UUID;

/**
 * @author Li Ming
 * @e-mail liming.school@qq.com
 * @create 2022-10-15 15:41
 */
public class UUIDUtil {
    /**
     * uuid32生成器
     * @return
     */
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
