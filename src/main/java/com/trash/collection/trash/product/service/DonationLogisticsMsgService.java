package com.trash.collection.trash.product.service;

import com.trash.collection.trash.product.domain.DonationLogisticsMsg;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 *  服务类
 *
 * @author seth
 * @since 2020-02-26
 */
public interface DonationLogisticsMsgService extends IService<DonationLogisticsMsg> {

    /**
     * 查询捐赠物品物流信息
     * */
    List<DonationLogisticsMsg> selectByDonationsGoodsId(Long donationsGoodsId);
}
