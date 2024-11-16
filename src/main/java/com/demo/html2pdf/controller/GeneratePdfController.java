package com.demo.html2pdf.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.demo.html2pdf.pojo.Project;
import com.demo.html2pdf.pojo.User;
import com.demo.html2pdf.util.HtmlToPdf;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "wk2Pdf")
public class GeneratePdfController {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    @Value("${pdf.wkhtmltopdfPath}")
    String wkhtmltopdfPath;

    @Value("${pdf.myoutlinePaht}")
    String myoutlinePaht;

    @Value("${pdf.localPdfPath}")
    String localPdfPath;

    @Value("${pdf.srcPath}")
    String srcPath;

    @Value("${pdf.coverUrl}")
    String coverUrl;

    @Value("${pdf.footerUrl}")
    String footerUrl;
    @RequestMapping(value = "/generatePdf")
    @ResponseBody
    public String generatePdf(Integer annualId, Integer type){
//在配置文件中配置localPdfPath路径，拼接模板路径和名字
        String pdfName = UUID.randomUUID() + ".pdf";
        String destPath = localPdfPath + pdfName;
        String webPath = localPdfPath + pdfName;
        HtmlToPdf htmlToPdf = new HtmlToPdf();
        //需要传参查询可以参考注释写法
//        String newCoverUrl = coverUrl + "?annualId=" + annualId;
//        String newSrcPath = srcPath + "?annualId=" + annualId;
//        htmlToPdf.convert(wkhtmltopdfPath,myoutlinePaht,newSrcPath,destPath, newCoverUrl, footerUrl);
        htmlToPdf.convert(wkhtmltopdfPath,myoutlinePaht,srcPath,destPath, coverUrl, footerUrl);
        return webPath;
    }
    @RequestMapping(value = "/getCover")
    public String getCover(Integer annualId, ModelMap map){
        User user = getUser();
        String jsonStr = JSONUtil.toJsonStr(user, new JSONConfig().setIgnoreNullValue(false));
        Map resMap = JSONUtil.toBean(jsonStr, Map.class);
        map.put("user", resMap);
        return "admin/cover";
    }
    @RequestMapping(value = "/getFooter")
    public String getFooter(Integer annualId, ModelMap map){
        return "admin/footer";
    }
    @RequestMapping(value = "/getTemplate")
    public String getTemplate(Integer annualId, ModelMap map){
        User user = getUser();
        String jsonStr = JSONUtil.toJsonStr(user, new JSONConfig().setIgnoreNullValue(false));
        Map resMap = JSONUtil.toBean(jsonStr, Map.class);
        map.put("user", resMap);
        return "admin/template";
    }
    private User getUser(){
        User user = new User();
        user.setName("张三");
        user.setAge(30);
        user.setEmail("zhangsan@example.com");
        user.setAddress("北京市朝阳区");
        user.setExperience(5);
        user.setDate(DateUtil.formatDateTime(new Date()));

        // 创建项目经验列表
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("项目A", "2018-2019", "后端开发", "负责项目A的后端开发工作"));
        projects.add(new Project("项目B", "2019-2020", "系统架构师", "负责项目B的系统架构设计"));
        projects.add(new Project("项目C", "2020-2021", "项目经理", "负责项目C的整体管理和协调"));

        user.setProjects(projects);

        return user;
    }

}