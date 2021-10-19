package com.bjpowernode.crm.controller;

import com.bjpowernode.crm.domain.Activity;
import com.bjpowernode.crm.domain.User;
import com.bjpowernode.crm.service.ActivityService;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/save.do")
    @ResponseBody
    public Map<String,Object> save(Activity activity, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        activity.setId(UUIDUtil.getUUID());

        //创建的当前系统时间
        activity.setCreateTime(DateTimeUtil.getSysTime());

        //创建人，为当前的登陆用户
        activity.setCreateBy(((User)request.getSession().getAttribute("user")).getName());



        boolean flag = activityService.save(activity);
        map.put("success", flag);
        return map;
    }

    @RequestMapping("/pageList.do")
    @ResponseBody
    public PaginationVo<Activity> pageList(String name,String owner,String startDate,String endDate,Integer pageNo,Integer pageSize){

        int beginIndex = (pageNo - 1) * pageSize;
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginIndex",beginIndex);
        map.put("pageSize",pageSize);


        PaginationVo<Activity> vo = activityService.pageList(map);
        return vo;
    }



}
