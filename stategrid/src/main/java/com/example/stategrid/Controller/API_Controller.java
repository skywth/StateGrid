package com.example.stategrid.Controller;

import com.example.stategrid.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class API_Controller {

//    @GetMapping("/stategrid/visualization/realtimedata/statistics/list")
//    public Result list() {
//        Map<String, Object> data = new LinkedHashMap<>();
//        data.put("total", 253);
//        data.put("selectedSelect", "201_51");
//        data.put("selectedNotStandard", 1);
//        data.put("special", 1);
//        data.put("estimateSurplus", "1000万_200万");
//        return Result.success(data);
//    }

    @PostMapping("/stategrid")
    public Result list_api(){
        Map<String, Object> data = new LinkedHashMap<>();
        Map<String, Object> m=new LinkedHashMap<>();
        List list=new ArrayList<>();
        data.put("m", m);
        data.put("l",list);
        return Result.success(data);
    }



}

