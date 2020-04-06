package com.trash.collection.trash.activity.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.trash.collection.trash.activity.dao.ActivityMsgMapper;
import com.trash.collection.trash.activity.service.ActivityMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public Page<ActivityMsg> selectPage(Page page, String param, Integer state) {
        if (Objects.isNull(state)) {
            state = ActivityMsg.NORMAL_STATE;
        }
        page.setTotal(this.baseMapper.getTotal(state));
        return page.setRecords(this.baseMapper.selectMsgList(page, param, state));
    }

    @Override
    @Transactional
    public void deleteActivity(Long activityId) {
        ActivityMsg activityMsg = new ActivityMsg().setId(activityId)
                .setState(ActivityMsg.FORBIDDEN_STATE)
                .setModifyTime(new Date());
        activityMsgMapper.updateById(activityMsg);
    }

    /**
     * 编辑活动信息
     * */
    public void updateMsg(ActivityMsg activityMsg){
        activityMsg.setModifyTime(new Date());
        activityMsgMapper.updateById(activityMsg);
    }
}
