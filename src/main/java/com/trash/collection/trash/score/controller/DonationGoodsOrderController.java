package com.trash.collection.trash.score.controller;


import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.domain.DonationLogisticsMsg;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.score.domain.DonationGoodsOrder;
import com.trash.collection.trash.score.service.DonationGoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 控制器
 *
 * @author seth
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/score/donationGoodsOrder")
public class DonationGoodsOrderController {

    @Autowired
    DonationGoodsOrderService goodsOrderService;

    @Autowired
    ProductKindService productKindService;

    /**
     * 设置上门回收工作人员信息
     * */
    @PostMapping("/settleWorker")
    public Response settleWorker(@RequestBody DonationGoodsOrder goodsOrder){
        Response response = new Response();
        if (Objects.isNull(goodsOrder.getWorkerMessageId())||Objects.isNull(goodsOrder.getId())){
            return productKindService.judgeParam();
        }
        goodsOrderService.setterWorker(goodsOrder);
        return response;
    }
}

