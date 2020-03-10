package com.trash.collection.trash.product.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.VO.DonationGoodsVO;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.baomidou.mybatisplus.service.IService;

/**
 *  服务类
 *
 * @author seth
 * @since 2020-02-26
 */
public interface DonationGoodsService extends IService<DonationGoods> {

    /**
     * 获取捐赠物品信息列表
     * */
    Page<DonationGoods> getDonationGoodsList(DonationGoodsVO goodsVO);

    /**
     * 更新捐赠物品的积分情况
     * */
    void setGoodsScore(DonationGoods donationGoods);
}