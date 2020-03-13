package com.trash.collection.trash.score.controller;


import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.score.VO.DonationGoodsOrderVO;
import com.trash.collection.trash.score.domain.DonationGoodsOrder;
import com.trash.collection.trash.score.service.DonationGoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     *获取捐赠物品订单列表
     * */
    @GetMapping("/list")
      public Response list(DonationGoodsOrderVO orderVo){
        Response response = new Response();
        response.setData(goodsOrderService.getGoodsOrderList(orderVo));
        return response;
    }

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

