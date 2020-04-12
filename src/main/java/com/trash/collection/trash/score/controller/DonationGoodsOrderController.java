package com.trash.collection.trash.score.controller;


import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.score.VO.DonationGoodsOrderVO;
import com.trash.collection.trash.score.VO.UserGoodsOrderVO;
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
@CrossOrigin
public class DonationGoodsOrderController {

    @Autowired
    DonationGoodsOrderService goodsOrderService;

    @Autowired
    ProductKindService productKindService;

    /**
     * 获取捐赠物品订单列表
     */
    @GetMapping("/list")
    public Response list(DonationGoodsOrderVO orderVo) {
        Response response = new Response();
        response.setData(goodsOrderService.getGoodsOrderList(orderVo));
        return response;
    }

    /**
     * 设置上门回收工作人员信息
     */
    @PostMapping("/settleWorker")
    public Response settleWorker(@RequestBody DonationGoodsOrder goodsOrder) {
        Response response = new Response();
        if (Objects.isNull(goodsOrder.getWorkerMessageId()) || Objects.isNull(goodsOrder.getId())) {
            return productKindService.judgeParam();
        }
        goodsOrderService.setterWorker(goodsOrder);
        return response;
    }

    /**
     * 用户获取自己的捐赠物品订单列表
     */
    @GetMapping("/getListByUser")
    public Response getListByUser(UserGoodsOrderVO userGoodsOrderVO) {
        if (Objects.isNull(userGoodsOrderVO)) {
            return productKindService.judgeParam();
        }
        Response response = new Response();
        response.setData(goodsOrderService.getListByUser(userGoodsOrderVO));
        return response;
    }

    /**
     * 用户下单（捐赠物品）
     * */
    @PostMapping("/placeOrder")
    public Response placeOrder(@RequestBody DonationGoodsOrder donationGoodsOrder){
        if (Objects.isNull(donationGoodsOrder)){
            return productKindService.judgeParam();
        }
        if (Objects.isNull(donationGoodsOrder.getUserAddressId())){
            return productKindService.judgeParam();
        }
        Response response = new Response();
        this.goodsOrderService.placeOrder(donationGoodsOrder);
        return response;
    }
}

