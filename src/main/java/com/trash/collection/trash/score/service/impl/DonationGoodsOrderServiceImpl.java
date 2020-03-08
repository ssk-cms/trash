package com.trash.collection.trash.score.service.impl;

import com.trash.collection.trash.product.domain.DonationLogisticsMsg;
import com.trash.collection.trash.product.service.DonationLogisticsMsgService;
import com.trash.collection.trash.score.domain.DonationGoodsOrder;
import com.trash.collection.trash.score.dao.DonationGoodsOrderMapper;
import com.trash.collection.trash.score.service.DonationGoodsOrderService;
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
public class DonationGoodsOrderServiceImpl extends ServiceImpl<DonationGoodsOrderMapper, DonationGoodsOrder> implements DonationGoodsOrderService {

    @Autowired
    private DonationGoodsOrderService goodsOrderService;

    @Autowired
    private DonationLogisticsMsgService logisticsMsgService;

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
                .setLogisticsMsgTitle("已安排工作人员上门")
                .setLogisticsMsgContent("已安排工作人员按照您预定的时间上门回收您捐赠的物品，如需查看工作人员信息，请在捐赠订单中查看！")
                .setCreateTime(date)
                .setModifyTime(date);
        logisticsMsgService.insert(logisticsMsg);
    }
}
