package com.trash.collection.trash.score.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.score.VO.DonationGoodsOrderVO;
import com.trash.collection.trash.score.VO.UserGoodsOrderVO;
import com.trash.collection.trash.score.domain.DonationGoodsOrder;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 服务类
 *
 * @author seth
 * @since 2020-03-07
 */
public interface DonationGoodsOrderService extends IService<DonationGoodsOrder> {

    /**
     * 获取捐赠物品订单列表
     * */
    Page<DonationGoodsOrderVO> getGoodsOrderList(DonationGoodsOrderVO orderVO);

    /**
     * 设置上门回收工作人员信息
     * */
    void setterWorker(DonationGoodsOrder goodsOrder);

    /**
     * 用户捐赠订单列表
     * */
    Page<DonationGoodsOrder> getListByUser(UserGoodsOrderVO userGoodsOrderVO);
}
