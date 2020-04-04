package com.trash.collection.trash.score.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.score.VO.ProductOrderVO;
import com.trash.collection.trash.score.domain.ProductOrder;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;

/**
 * 服务类
 *
 * @author seth
 * @since 2020-03-07
 */
public interface ProductOrderService extends IService<ProductOrder> {

    /**
     * 查看积分兑换订单列表
     * */
    Page<ProductOrderVO> getOrderList(ProductOrderVO orderVO);

    /**
     * 填写快递单号
     * */
    void updateTrackingNumber(ProductOrder productOrder);

    /**
     * 用户下单
     * */
    void setOrder(ProductOrder productOrder, BigDecimal needPoints);

    /**
     * 更新发货时间-快递单号
     * */
    void updateTime(ProductOrder productOrder);

    /**
     * 更新收货时间，变更订单状态
     * */
    void gainGoods(ProductOrder productOrder);
}
