package com.trash.collection.trash.score.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.trash.collection.trash.product.domain.DonationLogisticsMsg;
import com.trash.collection.trash.product.service.DonationGoodsService;
import com.trash.collection.trash.product.service.DonationLogisticsMsgService;
import com.trash.collection.trash.score.VO.DonationGoodsOrderVO;
import com.trash.collection.trash.score.VO.UserGoodsOrderVO;
import com.trash.collection.trash.score.domain.DonationGoodsOrder;
import com.trash.collection.trash.score.dao.DonationGoodsOrderMapper;
import com.trash.collection.trash.score.service.DonationGoodsOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-03-07
 */
@Service
public class DonationGoodsOrderServiceImpl extends ServiceImpl<DonationGoodsOrderMapper, DonationGoodsOrder> implements DonationGoodsOrderService {

    @Autowired
    private DonationGoodsOrderService goodsOrderService;

    @Autowired
    private DonationLogisticsMsgService logisticsMsgService;

    @Autowired
    private DonationGoodsService goodsService;

    @Autowired
    private DonationGoodsOrderMapper orderMapper;

    /**
     * 获取捐赠物品订单列表
     * */
    @Override
    public Page<DonationGoodsOrderVO> getGoodsOrderList(DonationGoodsOrderVO orderVO){
        Page<DonationGoodsOrderVO> page = new Page<>(orderVO.getPageIndex(),orderVO.getPageSize());
        List<DonationGoodsOrderVO> orderVOList = orderMapper.getGoodsOrderList(page,orderVO.getOrderNumber(),orderVO.getParam(),orderVO.getState());
        page.setRecords(orderVOList);
        return page;
    }

    /**
     * 设置上门回收工作人员信息
     * */
    @Override
    @Transactional
    public void setterWorker(DonationGoodsOrder goodsOrder){
        //在捐赠订单中设置上门工作人员信息
        this.setWorkerMessage(goodsOrder);
        //设置捐赠商品的物流信息
        this.setlogisticsMsg(goodsOrder);
        //修改捐赠商品中的捐赠物品状态
        this.setGoodsLogisticsStatus(goodsOrder);
    }

    /**
     * 用户捐赠订单列表
     * */
    @Override
    public Page<DonationGoodsOrder> getListByUser(UserGoodsOrderVO userGoodsOrderVO){
        Page<DonationGoodsOrder> page = new Page<>(userGoodsOrderVO.getPageIndex(),userGoodsOrderVO.getPageSize());
        page.setRecords(this.orderMapper.getListByUser(page,userGoodsOrderVO.getUserId(),userGoodsOrderVO.getState()));
        return page;
    }

    /**
     * 用户下单（捐赠物品）
     * */
    @Override
    @Transactional
    public void placeOrder(DonationGoodsOrder donationGoodsOrder){
        Date date = new Date();
        donationGoodsOrder.setCreateTime(date)
                .setModifyTime(date)
                .setGoodsOrderNumber(String.format("%1$s%2$s",
                        new SimpleDateFormat("yyyyMMddHHmmss").format(date), RandomStringUtils.randomNumeric(3)))
                .setState(1);
        this.baseMapper.insert(donationGoodsOrder);
        //设置捐赠商品的物流信息
        this.setlogisticsMsg(donationGoodsOrder);
        //修改捐赠商品中的捐赠物品状态
        this.setGoodsLogisticsStatus(donationGoodsOrder);
    }

    /**
     * 設置工作人員信息
     * */
    private void setWorkerMessage(DonationGoodsOrder goodsOrder) {
        DonationGoodsOrder order = new DonationGoodsOrder();
        order.setId(goodsOrder.getId())
                .setWorkerMessageId(goodsOrder.getWorkerMessageId())
                .setModifyTime(new Date());
        goodsOrderService.updateById(order);
    }

    /**
     * 设置捐赠物品的物流信息
     * */
    private void setlogisticsMsg(DonationGoodsOrder goodsOrder) {
        Date date = new Date();
        DonationLogisticsMsg logisticsMsg = new DonationLogisticsMsg();
        logisticsMsg.setDonateGoodsId(goodsOrder.getDonationGoodsId())
                .setDonationGoodsOrderId(goodsOrder.getId())
                .setWorkerMessageId(goodsOrder.getWorkerMessageId())
                .setLogisticsMsgTitle("等待工作人员上门")
                .setLogisticsMsgContent("您已下单，正等待工作人员上门！")
                .setCreateTime(date)
                .setModifyTime(date);
        logisticsMsgService.insert(logisticsMsg);
    }

    /**
     * 更新捐赠物品物流状态
     * */
    private void setGoodsLogisticsStatus(DonationGoodsOrder goodsOrder) {
        DonationGoods goods = new DonationGoods().setId(goodsOrder.getDonationGoodsId())
                .setLogisticsStatus(10)
                .setModifyTime(new Date());
        goodsService.updateById(goods);
    }
}
