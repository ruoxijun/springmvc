package ruoxijun.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ruoxijun.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class BookController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping(value="/allBook",produces="application/json;charset=utf-8")
    @ResponseBody
    public String allBook(String mgs) throws JsonProcessingException {
        System.err.println("mgs===>"+mgs);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(bookService.queryBooks()));
        return mapper.writeValueAsString(bookService.queryBooks());
    }

    @RequestMapping("/data")
    public String data(){
        return "data";
    }

    @RequestMapping(value = "/file")
    public String file(@RequestParam(value = "file") CommonsMultipartFile file,
                       HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        File path = new File(request.getServletContext().getRealPath("/file"));
        if (!path.exists()){
            path.mkdir();
            System.out.println("文件夹创建成功");
        }
        File filePath = new File(path,fileName);
        InputStream in = file.getInputStream();
        OutputStream out = new FileOutputStream(filePath);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer))!=-1){
            out.write(buffer,0,len);
            System.out.println("写入文件："+len+"字节");
            out.flush();
        }
        out.close();
        in.close();
        return "redirect:/";
    }

    @RequestMapping(value = "/download")
    public String file(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition","attachment;fileName="+
                URLEncoder.encode("wallhaven-eyg6l8.jpg","utf-8"));
        File path = new File(request.getServletContext().getRealPath("/file"));
        File filePath = new File(path,"wallhaven-eyg6l8.jpg");
        InputStream in = new FileInputStream(filePath);
        OutputStream out = response.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer))!=-1){
            out.write(buffer,0,len);
            System.out.println("写出文件："+len+"字节");
            out.flush();
        }
        out.close();
        in.close();
        return "redirect:/";
    }
}
