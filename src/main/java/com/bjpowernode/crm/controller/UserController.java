package com.bjpowernode.crm.controller;

import com.bjpowernode.crm.domain.User;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService service;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(String loginAct, String loginPwd, HttpServletRequest request, HttpServletResponse response){

        loginPwd = MD5Util.getMD5(loginPwd);

        String ip = request.getRemoteAddr();
        Map<String,Object> map = new HashMap<>();

        //登陆失败走catch,无异常说明登陆成功
        try{
            User user = service.queryUser(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user", user);
            map.put("success", false);

            return map;

        }catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();


            map.put("success", false);
            map.put("msg",msg);

            return map;


        }


    }

}
