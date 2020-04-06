package com.trash.collection.trash.activity.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author seth
 * @since 2020-01-19
 */
@Mapper
public interface ActivityMsgMapper extends BaseMapper<ActivityMsg> {

    /**
     * 查看活动列表
     */
    List<ActivityMsg> selectMsgList(Page page,@Param("param") String param,@Param("state") Integer state);

    /**
     * 查询列表所有的数据
     *
     * @param state*/
    int getTotal(Integer state);
}
