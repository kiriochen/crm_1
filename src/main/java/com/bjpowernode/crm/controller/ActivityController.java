package com.bjpowernode.crm.controller;

import com.bjpowernode.crm.domain.User;
import com.bjpowernode.crm.service.ActivityService;
import com.bjpowernode.crm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/workbench/activity")
@Controller
public class ActivityController {

    @Resource
    ActivityService activityService;

    @Resource
    UserService userService;

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        List<User> user = userService.getUserList();
        return user;
    }

}
