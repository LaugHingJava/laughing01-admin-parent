package com.laughing.crowd.controller;

import com.laughing.crowd.testpojo.Student;
import com.laughing.crowd.util.CrowdUtil;
import com.laughing.crowd.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @GetMapping("/test/aaa")
    public Object test(){
        System.out.println("dddddd");
        return "test";
    }
    @ResponseBody
    @RequestMapping("/test/ajax.json")
    public Object ajaxTest(@RequestBody Student student){
        System.out.println(student.toString());
        return student;
    }

    @ResponseBody
    @RequestMapping("/test/ajaxObject.json")
    public Object ajaxObject(@RequestBody Student student){
        System.out.println(student.toString());
        ResultEntity resultEntity = new ResultEntity("success","成功",student);
        return resultEntity;
    }
    //测试异常xml配置

    @GetMapping("/test/exc")
    public Object excTest(){
        System.out.println("test");
        System.out.println(10/0);
        ResultEntity resultEntity = new ResultEntity("fail","异常",null);
        return "test";
    }




}
