package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.dao.ActivityDao;
import com.bjpowernode.crm.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    ActivityDao activityDao;
}
