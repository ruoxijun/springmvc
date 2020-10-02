package io.ruoxijun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.ruoxijun.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class HelloController {

    // produces解决乱码问题
    @RequestMapping(value="/hello",produces="application/json;charset=utf-8")
    // 使该方法不通过视图解析器，直接返回字符串
    @ResponseBody
    public String hello() {
        return "hello";
    }

}