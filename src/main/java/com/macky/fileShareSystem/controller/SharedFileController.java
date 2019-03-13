package com.macky.fileShareSystem.controller;

import com.macky.fileShareSystem.dto.ServerResponse;
import com.macky.fileShareSystem.entity.SharedUser;
import com.macky.fileShareSystem.service.SharedFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/13 21:52
 */
@Controller
public class SharedFileController {

    @Resource
    private SharedFileService sharedFileService;


     /**
     *查找当前用户的所有文件
     * @param session
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/list_private.do")
    public String listFileByUser(HttpSession session, Model model, @RequestParam(defaultValue = "1") String page){
        SharedUser user = (SharedUser) session.getAttribute("user");
        String userId = user.getuId();
        ServerResponse serverResponse = sharedFileService.listFileByUser(userId);
        model.addAttribute("res", serverResponse.getData());
        return "private_file";
    }

    /**
     * 查找当前系统所有共享的文件（不包括当前用户的共享文件）
     * @param session
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/list_shared.do")
    public String listFileByShared(HttpSession session, Model model, @RequestParam(defaultValue = "1") String page){
        ServerResponse serverResponse = sharedFileService.listFileByShared();
        model.addAttribute("res", serverResponse.getData());
        return "shared_file";
    }

    /**
     * 上传文件
     * @param fileUpload
     * @return
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public String uploadFile(@RequestParam ("file") CommonsMultipartFile fileUpload, HttpSession session){
        ServerResponse serverResponse = sharedFileService.saveFile(fileUpload, session);
        return serverResponse.getMsg();
    }

    /**
     * 更新文件的状态 共享
     * @param id
     * 0:无效 1:私有 2:共享
     * @return
     */
    @RequestMapping(value = "/share.do")
    public String updateFileStateShare(String id){
        ServerResponse serverResponse = sharedFileService.updateFileState(id, "2");
        if (serverResponse.isSuccess()){
            return "shared_success";
        }
        return "operation_error";
    }

    /**
     * 更新文件的状态 删除
     * @param id
     * 0:无效 1:私有 2:共享
     * @return
     */
    @RequestMapping(value = "/delete.do")
    public String updateFileStateDelete(String id){
        ServerResponse serverResponse = sharedFileService.updateFileState(id, "0");
        if (serverResponse.isSuccess()){
            return "delete_success";
        }
        return "operation_error";
    }
}
