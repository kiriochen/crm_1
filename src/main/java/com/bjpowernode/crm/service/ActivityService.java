package com.bjpowernode.crm.service;

import com.bjpowernode.crm.domain.Activity;
import com.bjpowernode.crm.vo.PaginationVo;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity activity);
    PaginationVo<Activity> pageList(Map<String,Object> map);
}
