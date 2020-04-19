package com.trash.collection.trash.score.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.score.VO.ProductOrderVO;
import com.trash.collection.trash.score.domain.ProductOrder;
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
public interface ProductOrderMapper extends BaseMapper<ProductOrder> {

    /**
     * 查看积分兑换订单列表
     */
    List<ProductOrderVO> getOrderList(Page<ProductOrderVO> page, @Param("param") String param, @Param("state") Integer state,@Param("orderNumber") String orderNum);

    /**
     * 用户查看自己的积分兑换订单列表
     */
    List<ProductOrder> getListByUser(Page<ProductOrder> page, @Param("userId") Integer userId, @Param("state") Integer state);
}
