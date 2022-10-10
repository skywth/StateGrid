package src.main.java.com.example.stategrid.Controller;

import com.example.stategrid.Mapper.Class4_Mapper;
import com.example.stategrid.common.Page;
import com.example.stategrid.common.Result;
import com.example.stategrid.common.Result_List;
import com.example.stategrid.entity.test7_entity;
import com.example.stategrid.entity.test8_entity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Class4_Controller {
    @Resource
    com.example.stategrid.Mapper.Class4_Mapper test7_Mapper;
    @Resource
    com.example.stategrid.Mapper.Class4_Mapper test8_Mapper;

    @GetMapping("/stategrid/stategridinfo/schedule/schedule/list")
    public Result list_schedule() {
        Map<String, Object> data = new LinkedHashMap<>();     //data以Map形式
//        data.put("date", test7_Mapper.findAll().get(0).getDate());
//        data.put("day", test7_Mapper.findAll().get(0).getDay());
//        data.put("name",test7_Mapper.findAll().get(0).getName());
//        data.put("gap", test7_Mapper.findAll().get(0).getGap());
        data.put("data", test7_Mapper.findAll_test7());
        return Result.success(data);
    }
        @GetMapping("/stategrid/stategridinfo/schedule/monitor/list")
        public Result list_monitor() {
            Map<String, Object> data = new LinkedHashMap<>();     //data以Map形式
//        data.put("date", test8_Mapper.findAll().get(0).getDate());
//        data.put("day", test8_Mapper.findAll().get(0).getDay());
//        data.put("name",test8_Mapper.findAll().get(0).getName());
//        data.put("progress", test8_Mapper.findAll().get(0).getProgress());
            data.put("data", test8_Mapper.findAll_test8());
            return Result.success(data);
        }
    @GetMapping("/stategrid/stategridinfo/schedule/schedule/download")
    public Result result_Download(){
        Map<String, Object> data = new LinkedHashMap<>();
        data=null;
        return Result.success(data);
    }//安排表下载

    @GetMapping("/stategrid/stategridinfo/schedule/monitor/download")
    public Result result_Download(){
        Map<String, Object> data = new LinkedHashMap<>();
        data=null;
        return Result.success(data);
    }//实时监控表下载

    @PostMapping("/stategrid/stategridinfo/schedule/schedule/push")
    public Result addInfo_test7(@RequestBody com.example.stategrid.entity.test7_entity test7_info){
        Map<String, Object> data = new LinkedHashMap<>();
        if(test7_info.getDate()==null || test7_info.getDay()==null|| test7_info.getName()==null || test7_info.getGap()==null)
            return Result.error("推送失败");
        else {
            test_Mapper.addInfo_test7(test7_info);
            return Result.success(data);
        }
    }//推送接口

}
