package com.trash.collection.trash.product.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author seth
 * @since 2020-02-26
 */
@Mapper
public interface DonationGoodsMapper extends BaseMapper<DonationGoods> {

    /**
     * 获取捐赠物品列表
     */
    List<DonationGoods> getDonationGoodsList(Page<DonationGoods> page, @Param("state") Integer state,
                                             @Param("goodsName") String goodsName, @Param("productKindId") Long productKindId);

    /**
     * 获取用户自己捐赠物品列表
     */
    List<DonationGoods> getListByUser(Page<DonationGoods> page, @Param("userId") Long userId,
                                      @Param("state") Integer state, @Param("goodsName") String goodsName);
}
