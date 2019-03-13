//package com.macky.fileShareSystem.controller;
//
//import com.macky.fileShareSystem.dto.ServerResponse;
//import com.macky.fileShareSystem.service.DesignTemplateService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * @author: MackyHuang
// * @eamil: 973151766@qq.com
// * @createTime: 2018/12/5 21:09
// */
//@Controller
//public class UploadExcelController {
//
//    @Resource
//    private DesignTemplateService designTemplateService;
//
//    @RequestMapping(value = "/upload.do")
//    @ResponseBody
//    public String uplocalExcel(@RequestParam ("file") CommonsMultipartFile fileUpload, HttpServletRequest request, HttpServletResponse response, HttpSession session){
//        //OriginalName:（大二）2016级计算机科学与技术专业学生综合测评成绩 (1).xls
//        System.out.println("OriginalName:" + fileUpload.getOriginalFilename());
//        //Size:187392
//        System.out.println("Size:" + fileUpload.getSize());
//        ServerResponse serverResponse = designTemplateService.savaCompleteTemplate(fileUpload, "Test", session);
//        return serverResponse.getMsg();
//    }
//
//    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
//    public String index(){
//        return "design_list";
//    }
//}
