package io.ruoxijun;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 使用Controller注解不用再继承Controller接口，且自动注册为bean
@Controller
// 可在类上再加一层请求路径的映射,也可省略
//@RequestMapping("/controller")
public class HelloController {
    // 映射请求路径
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msg","hello springmvc");
        // 转发方式一：
         return "/index.jsp";
        // 转发方式二：使用关键字forward
        // return "forward:/index.jsp";
        /**
         * 重定向：使用关键字redirect，且有视图解析器时重定向也能正常使用
         * 重定向本质就是重新请求所以不一定要返回路径也可以是一个新请求
         */
//        return "redirect:/index.jsp";
    }

    // 大括号指明变量值在url中映射的位置

    /**
     * 大括号指明变量值在url中映射的位置，
     * 变量在url中的位置和个数可以随意更改，
     * 但必须给所有路径变量声明并赋值
     */
    @RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.POST)
    // @PathVariable 注解的变量表示它是一个路径变量
    public String add(@PathVariable int a, @PathVariable int b, Model model){
        model.addAttribute("msg","两数和为："+(a+b));
        return "hello";
    }
}
