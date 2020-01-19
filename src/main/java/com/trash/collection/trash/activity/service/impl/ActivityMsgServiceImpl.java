package com.trash.collection.trash.activity.service.impl;

import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.trash.collection.trash.activity.dao.ActivityMsgMapper;
import com.trash.collection.trash.activity.service.ActivityMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author seth
 * @since 2020-01-19
 */
@Service
public class ActivityMsgServiceImpl extends ServiceImpl<ActivityMsgMapper, ActivityMsg>implements ActivityMsgService {

}
