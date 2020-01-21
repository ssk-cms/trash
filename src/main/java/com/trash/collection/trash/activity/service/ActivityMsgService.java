package com.trash.collection.trash.activity.service;

import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.baomidou.mybatisplus.service.IService;

/**
 *  服务类
 *
 * @author seth
 * @since 2020-01-19
 */
public interface ActivityMsgService extends IService<ActivityMsg> {

    void addActivity(ActivityMsg activityMsg);

}
