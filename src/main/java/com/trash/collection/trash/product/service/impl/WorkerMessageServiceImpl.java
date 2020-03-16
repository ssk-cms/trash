package com.trash.collection.trash.product.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLPort;
import com.trash.collection.trash.product.VO.WorkerMessageVO;
import com.trash.collection.trash.product.domain.WorkerMessage;
import com.trash.collection.trash.product.dao.WorkerMessageMapper;
import com.trash.collection.trash.product.service.WorkerMessageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-03-08
 */
@Service
public class WorkerMessageServiceImpl extends ServiceImpl<WorkerMessageMapper, WorkerMessage> implements WorkerMessageService {

    @Autowired
    WorkerMessageService messageService;

    @Autowired
    WorkerMessageMapper workerMessageMapper;

    /**
     * 新增工作人员信息
     * */
    @Override
    @Transactional
    public void add(WorkerMessage workerMessage){
        Date date = new Date();
        workerMessage.setState(1)
                .setCreateTime(date)
                .setModifyTime(date);
        messageService.insert(workerMessage);
    }

    /**
     * 查看工作人员列表
     * */
    @Override
    public Page<WorkerMessage> getList(WorkerMessageVO workerMessageVO){
        Page<WorkerMessage> page = new Page<>(workerMessageVO.getPageIndex(),workerMessageVO.getPageSize());
        page.setRecords(workerMessageMapper.getList(page,workerMessageVO.getParam(), workerMessageVO.getState()));
        return page;
    }

    /**
     * 编辑工作人员信息
     * */
    @Override
    @Transactional
    public void edit(WorkerMessage workerMessage){
        workerMessage.setModifyTime(new Date());
        messageService.updateById(workerMessage);
    }
}
