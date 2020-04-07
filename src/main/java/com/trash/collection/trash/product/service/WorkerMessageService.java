package com.trash.collection.trash.product.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.VO.WorkerMessageVO;
import com.trash.collection.trash.product.domain.WorkerMessage;
import com.baomidou.mybatisplus.service.IService;

/**
 * 服务类
 *
 * @author seth
 * @since 2020-03-08
 */
public interface WorkerMessageService extends IService<WorkerMessage> {

    /**
     * 新增工作人员信息
     * */
    void add(WorkerMessage workerMessage);

    /**
     * 查看工作人员列表
     * */
    Page<WorkerMessage> getList(WorkerMessageVO workerMessageVO);

    /**
     * 编辑工作人员信息
     * */
    void edit(WorkerMessage workerMessage);

    /**
     * 改变工作人员状态为离职
     * */
    void editState(Long workerId);
}
