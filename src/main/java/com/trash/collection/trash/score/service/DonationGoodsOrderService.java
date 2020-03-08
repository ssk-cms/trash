package com.trash.collection.trash.score.service;

import com.trash.collection.trash.score.domain.DonationGoodsOrder;
import com.baomidou.mybatisplus.service.IService;

/**
 * 服务类
 *
 * @author seth
 * @since 2020-03-07
 */
public interface DonationGoodsOrderService extends IService<DonationGoodsOrder> {

    /**
     * 设置上门回收工作人员信息
     * */
    void setterWorker(DonationGoodsOrder goodsOrder);
}
