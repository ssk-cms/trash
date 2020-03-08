package com.trash.collection.trash.product.service.impl;

import com.trash.collection.trash.product.domain.WorkerMessage;
import com.trash.collection.trash.product.dao.WorkerMessageMapper;
import com.trash.collection.trash.product.service.WorkerMessageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-03-08
 */
@Service
public class WorkerMessageServiceImpl extends ServiceImpl<WorkerMessageMapper, WorkerMessage> implements WorkerMessageService {

}
