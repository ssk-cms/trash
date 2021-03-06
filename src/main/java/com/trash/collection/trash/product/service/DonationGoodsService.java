package com.trash.collection.trash.product.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.VO.DonationGoodsVO;
import com.trash.collection.trash.product.VO.GoodsListVO;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

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
    Page<GoodsListVO> getDonationGoodsList(DonationGoodsVO goodsVO);

    /**
     * 更新捐赠物品的积分情况
     * */
    void setGoodsScore(DonationGoods donationGoods);

    /**
     * 更新捐赠物品物流状态--安排工作人员上门
     * */
    void arrangeWorrker(DonationGoods donationGoods);

    /**
     * 更新捐赠物品物流状态--商品入库
     * */
    void putInStorage(DonationGoods donationGoods);

    /**
     * 更新捐赠物品物流状态--商品出库
     * */
    void stockRemove(DonationGoods donationGoods);

    /**
     * 用户查看自己捐赠物品列表
     * */
    Page<DonationGoods> getListByUser(DonationGoodsVO donationGoodsVO);

    /**
     * 新增捐赠商品信息
     * */
    void add(DonationGoods donationGoods);

    /**
     * 获取最近一个月内捐赠物品达【10】件物品的用户id列表
     * */
    List<Integer> getUserByScore();
}
