package com.trash.collection.trash.activity.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.baomidou.mybatisplus.service.IService;

/**
 * 服务类
 *
 * @author seth
 * @since 2020-01-19
 */
public interface ActivityMsgService extends IService<ActivityMsg> {

    void addActivity(ActivityMsg activityMsg);

    Page<ActivityMsg> selectPage(Page<ActivityMsg> page, String param, Integer state);

    void deleteActivity(Long activityId);
}
