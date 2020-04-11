package com.trash.collection.trash.product.dao;

import com.trash.collection.trash.product.domain.DonationLogisticsMsg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author seth
 * @since 2020-02-26
 */
@Mapper
public interface DonationLogisticsMsgMapper extends BaseMapper<DonationLogisticsMsg> {

    /**
     * 查询捐赠物品物流信息列表
     * */
    List<DonationLogisticsMsg> selectByDonationsGoodsId(Long donationsGoodsId);
}
