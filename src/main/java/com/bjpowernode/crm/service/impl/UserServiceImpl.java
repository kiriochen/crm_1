package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.dao.UserDao;
import com.bjpowernode.crm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

}
