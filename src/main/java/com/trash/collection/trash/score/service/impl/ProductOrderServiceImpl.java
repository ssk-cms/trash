package com.trash.collection.trash.score.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.score.VO.ProductOrderVO;
import com.trash.collection.trash.score.domain.ProductOrder;
import com.trash.collection.trash.score.dao.ProductOrderMapper;
import com.trash.collection.trash.score.service.ProductOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-03-07
 */
@Service
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder> implements ProductOrderService {

    @Autowired
    ProductOrderMapper orderMapper;

    /**
     * 积分兑换商品订单列表
     * */
    @Override
    public Page<ProductOrderVO> getOrderList(ProductOrderVO orderVO){
        Page<ProductOrderVO> page = new Page<>(orderVO.getPageIndex(),orderVO.getPageSize());
        page.setRecords(orderMapper.getOrderList(page,orderVO.getParam()));
        return page;
    }

    /**
     * 填写快递单号
     * */
    @Override
    @Transactional
    public void updateTrackingNumber(ProductOrder productOrder){
        ProductOrder order = new ProductOrder();
        order.setId(productOrder.getId())
                .setTrackingNumber(productOrder.getTrackingNumber())
                .setModifyTime(new Date());
        this.orderMapper.updateById(order);
    }
}
