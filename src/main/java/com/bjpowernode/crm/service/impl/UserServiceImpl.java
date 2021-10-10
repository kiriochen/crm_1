package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.dao.UserDao;
import com.bjpowernode.crm.domain.User;
import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User queryUser(String loginAct, String loginPwd, String ip) throws LoginException{
        Map<String,String> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd",loginPwd);
        User user = userDao.selectUser(map);

        if(user == null){
            throw new LoginException("账号密码错误");
        }

        //到这说明账号密码正确

        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime) < 0){
            throw new LoginException("账号已失效");
        }

        String lockState = user.getLockState();
        if("0".equals(lockState)){
            throw new LoginException("账号已锁定");
        }

        String allowIp = user.getAllowIps();
        if(!allowIp.contains(ip)){
            throw new LoginException("ip地址受限");
        }


        return user;

    }
}
