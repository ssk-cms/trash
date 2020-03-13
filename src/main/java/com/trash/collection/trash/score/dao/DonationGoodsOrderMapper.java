package com.trash.collection.trash.score.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.score.VO.DonationGoodsOrderVO;
import com.trash.collection.trash.score.domain.DonationGoodsOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author seth
 * @since 2020-03-07
 */
@Mapper
public interface DonationGoodsOrderMapper extends BaseMapper<DonationGoodsOrder> {

    List<DonationGoodsOrderVO> getGoodsOrderList(Page<DonationGoodsOrderVO> page, @Param("param") String param,
                                                 @Param("orderNumber") String orderNumber,
                                                 @Param("state") Integer state);
}
