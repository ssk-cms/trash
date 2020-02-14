package com.trash.collection.trash.activity.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.trash.collection.trash.activity.dao.ActivityMsgMapper;
import com.trash.collection.trash.activity.service.ActivityMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-01-19
 */
@Service
public class ActivityMsgServiceImpl extends ServiceImpl<ActivityMsgMapper, ActivityMsg> implements ActivityMsgService {


    @Autowired
    ActivityMsgMapper activityMsgMapper;

    @Override
    @Transactional
    public void addActivity(ActivityMsg activityMsg) {
        Date date = new Date();
        activityMsg.setCreateTime(date)
                .setModifyTime(date)
                .setState(ActivityMsg.NORMAL_STATE);
        activityMsgMapper.insert(activityMsg);
    }

    @Override
    public Page<ActivityMsg> selectPage(Page<ActivityMsg> page, String param, Integer state) {
        if (Objects.isNull(state)) {
            state = ActivityMsg.NORMAL_STATE;
        }
        List<ActivityMsg> activityMsgList = activityMsgMapper.selectMsgList(page, param, state);
        page.setRecords(activityMsgList);
        return page;
    }

    @Override
    @Transactional
    public void deleteActivity(Long activityId) {
        ActivityMsg activityMsg = new ActivityMsg().setId(activityId)
                .setState(ActivityMsg.FORBIDDEN_STATE)
                .setModifyTime(new Date());
        activityMsgMapper.updateById(activityMsg);
    }
}
