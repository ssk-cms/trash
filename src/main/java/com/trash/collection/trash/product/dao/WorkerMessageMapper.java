package com.trash.collection.trash.product.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.domain.WorkerMessage;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author seth
 * @since 2020-03-08
 */
@Mapper
public interface WorkerMessageMapper extends BaseMapper<WorkerMessage> {

    /**
     * 获取工作人员信息列表
     * */
    List<WorkerMessage> getList(Page<WorkerMessage> page, String param, Integer state);
}
