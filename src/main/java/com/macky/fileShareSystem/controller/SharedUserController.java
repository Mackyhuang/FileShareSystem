package com.macky.fileShareSystem.controller;

import com.github.pagehelper.PageInfo;
import com.macky.fileShareSystem.common.CommUtil;
import com.macky.fileShareSystem.common.mail.MailUtil;
import com.macky.fileShareSystem.common.mail.MailUtilBy587;
import com.macky.fileShareSystem.dto.ServerResponse;
import com.macky.fileShareSystem.entity.SharedUser;
import com.macky.fileShareSystem.enums.state.SharedState;
import com.macky.fileShareSystem.service.SharedFileService;
import com.macky.fileShareSystem.service.SharedUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/12/13 22:13
 */
@Controller
public class SharedUserController {

    @Resource
    private SharedUserService sharedUserService;

    @Resource
    private SharedFileService sharedFileService;

    @RequestMapping(value = "login_page.do")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "register_page.do")
    public String registerPage(){
        return "register";
    }

    /**
     * 获取所有用户
     * @return
     */
    @RequestMapping(value = "user_list.do")
    public String userList(Model model, @RequestParam(defaultValue = "1") String page){
        ServerResponse serverResponse = sharedUserService.listUser();
        model.addAttribute("res", serverResponse.getData());
        return "user_list";
    }



    @RequestMapping(value = "login.do")
    public String loginCheck(Model model, String username, String password, HttpSession session){
        ServerResponse serverResponse = sharedUserService.loginCheck(username, password);
        if (serverResponse.isSuccess()){
            ServerResponse user = sharedUserService.getUser(username);
            SharedUser data = (SharedUser) user.getData();
            session.setAttribute("user", data);
            String userId = ((SharedUser)user.getData()).getuId();
            ServerResponse countUser = sharedUserService.countUser();
            ServerResponse countSharedFile = sharedFileService.countSharedFile();
            ServerResponse countUserFile = sharedFileService.countUserFile(userId);
            ServerResponse countUserSharedFile = sharedFileService.countUserSharedFile(userId);
            model.addAttribute("countUser", countUser.getData());
            model.addAttribute("countSharedFile", countSharedFile.getData());
            model.addAttribute("countUserFile", countUserFile.getData());
            model.addAttribute("countUserSharedFile", countUserSharedFile.getData());
            return "index";
        }
        return "error";
    }

    @RequestMapping(value = "index.do")
    public String index(String username, String password, HttpSession session, Model model){
        SharedUser user = (SharedUser) session.getAttribute("user");
        String userId = user.getuId();
        ServerResponse countUser = sharedUserService.countUser();
        ServerResponse countSharedFile = sharedFileService.countSharedFile();
        ServerResponse countUserFile = sharedFileService.countUserFile(userId);
        ServerResponse countUserSharedFile = sharedFileService.countUserSharedFile(userId);
        model.addAttribute("countUser", countUser.getData());
        model.addAttribute("countSharedFile", countSharedFile.getData());
        model.addAttribute("countUserFile", countUserFile.getData());
        model.addAttribute("countUserSharedFile", countUserSharedFile.getData());
        return "index";
    }

    /**
     * 修改密码
     * @param password
     * @return
     */
    @RequestMapping(value = "change.do")
    public String changePassword(String username, String password, HttpSession session){
        ServerResponse serverResponse = sharedUserService.changePassword(username, CommUtil.getMD5(password));
        if (serverResponse.isSuccess()){
            return "remake_success";
        }
        return "operation_error";
    }

    @RequestMapping(value = "remake.do")
    public String remake_page(HttpSession session){
        return "remake";
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do")
    public String registerUser(SharedUser user, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        System.out.println(user.getuName() + "__" + user.getuName());
        user.setCreateTime(new Date());
        user.setuId(CommUtil.getUUID());
        user.setuPassword(CommUtil.getMD5(user.getuPassword()));
        ServerResponse serverResponse = sharedUserService.saveUser(user);
        System.out.println(serverResponse.getMsg());
        if (serverResponse.isSuccess()){
            return "login";
        }
        return "register_error";
    }

    @RequestMapping(value = "logout.do")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping(value = "mail.do")
    public String sendmail(Model model, String mail){
        if (StringUtils.isEmpty(mail)){
            return "operation_error";
        }
        try {
            MailUtilBy587.sendMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sub = mail.substring(mail.lastIndexOf('@') + 1, mail.lastIndexOf('.'));
        if (sub.equals("qq")){
            model.addAttribute("link", "https://mail.qq.com/cgi-bin/loginpage");
        }else if (sub.equals("163")){
            model.addAttribute("link", "https://mail.163.com/");
        }else if (sub.equals("sohu")){
            model.addAttribute("link", "https://mail.sohu.com/fe/#/login");
        }else {
            model.addAttribute("link", "https://mail.qq.com/cgi-bin/loginpage");
        }
        return "mail_success";
    }
}
