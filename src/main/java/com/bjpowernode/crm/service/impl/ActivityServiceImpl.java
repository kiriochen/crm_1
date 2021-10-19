package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.dao.ActivityDao;
import com.bjpowernode.crm.domain.Activity;
import com.bjpowernode.crm.service.ActivityService;
import com.bjpowernode.crm.vo.PaginationVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    ActivityDao activityDao;


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = false,
    rollbackFor = {RuntimeException.class})
    @Override
    public boolean save(Activity activity) {
        boolean flag = true;

        int count = activityDao.save(activity);

        if(count != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public PaginationVo<Activity> pageList(Map<String, Object> map) {
        int total = activityDao.getTotalByCondition(map);

        List<Activity> list = activityDao.getActivityListByCondition(map);

        PaginationVo<Activity> vo = new PaginationVo<>();

        vo.setTotal(total);
        vo.setDataList(list);

        return vo;
    }
}
