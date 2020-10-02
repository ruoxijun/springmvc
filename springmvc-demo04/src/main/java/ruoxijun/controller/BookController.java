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

    /* // 1.使用getInputStream获取流，保存文件
    @RequestMapping(value = "/file")
    // 将input的name为file的值传递给CommonsMultipartFile对象
    public String file(@RequestParam(value = "file") CommonsMultipartFile file,
                       HttpServletRequest request) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 创建存放文件的未文件夹
        File path = new File(request.getServletContext().getRealPath("/file"));
        if (!path.exists()){
            path.mkdir();
        }
        // 获取上传文件的文件流
        InputStream in = file.getInputStream();
        // 存放文件
        File filePath = new File(path,fileName);
        OutputStream out = new FileOutputStream(filePath);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer))!=-1){
            out.write(buffer,0,len);
            out.flush();
        }
        out.close();
        in.close();
        return "redirect:/";
    }
     */

    // 2.使用transferTo方法，保存文件
    @RequestMapping(value = "/file")
    public String file(@RequestParam(value = "file") CommonsMultipartFile file,
                       HttpServletRequest request) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 创建存放文件的未文件夹
        File path = new File(request.getServletContext().getRealPath("/file"));
        if (!path.exists()){
            path.mkdir();
        }
        // 存放文件
        File filePath = new File(path,fileName);
        file.transferTo(filePath);
        return "redirect:/";
    }

    @RequestMapping(value = "/download")
    public String file(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 设置页面不缓存,清空buffer
        response.reset();
        // 设置字符编码
        response.setCharacterEncoding("utf-8");
        // 二进制传输数据
        response.setContentType("multipart/form-data");
        // 设置响应头
        response.setHeader("Content-Disposition","attachment;fileName="+
                URLEncoder.encode("wallhaven-eyg6l8.jpg","utf-8"));
        // 读取文件
        File path = new File(request.getServletContext().getRealPath("/file"));
        File filePath = new File(path,"wallhaven-eyg6l8.jpg");
        InputStream in = new FileInputStream(filePath);
        // 获取传输文件输出流
        OutputStream out = response.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer))!=-1){
            out.write(buffer,0,len);
            out.flush();
        }
        out.close();
        in.close();
        return "redirect:/";
    }
}
