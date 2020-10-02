import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.*;

// 注意：这里导入的是Controller接口
public class HelloController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        // ModelAndView 模型和视图类
        ModelAndView mv = new ModelAndView();
        // 封装对象，放在ModelAndView中。
        mv.addObject("msg","HelloSpringMVC!");
        // 封装要跳转的视图，放在ModelAndView中
        mv.setViewName("hello"); //: /WEB-INF/jsp/hello.jsp
        return mv;
    }
}