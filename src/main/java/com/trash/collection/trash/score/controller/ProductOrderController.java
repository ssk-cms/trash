package com.trash.collection.trash.score.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.domain.Product;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.product.service.ProductService;
import com.trash.collection.trash.score.VO.ProductOrderVO;
import com.trash.collection.trash.score.domain.ProductOrder;
import com.trash.collection.trash.score.domain.ScoreUser;
import com.trash.collection.trash.score.service.ProductOrderService;
import com.trash.collection.trash.score.service.ScoreUserService;
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
@RequestMapping("/score/productOrder")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private ProductKindService productKindService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ScoreUserService scoreUserService;

    @GetMapping("/list")
    public Response list(ProductOrderVO orderVO) {
        Response response = new Response();
        response.setData(productOrderService.getOrderList(orderVO));
        return response;
    }

    /**
     * a填写快递单号
     */
    @PostMapping("/updateTrackingNumber")
    public Response updateTrackingNumber(@RequestBody ProductOrder productOrder) {
        Response response = new Response();
        if (Objects.isNull(productOrder.getId()) || Objects.isNull(productOrder.getTrackingNumber())) {
            return productKindService.judgeParam();
        }
        productOrderService.updateTrackingNumber(productOrder);
        return response;
    }

    /**
     * 下单积分捐赠商品
     */
    @PostMapping("/addOrder")
    public Response addOrder(@RequestBody ProductOrder productOrder) {
        //判断传入参数是否为空
        if (Objects.isNull(productOrder)) {
            return this.productKindService.judgeParam();
        }
        if (Objects.isNull(productOrder.getProductId()) || Objects.isNull(productOrder.getUserId())) {
            return this.productKindService.judgeParam();
        }
        Response response = new Response();
        //根据商品id查询商品兑换所需积分
        Product product = this.productService.selectById(productOrder.getProductId());
        //根绝用户id查询用户当前拥有的积分
        ScoreUser scoreUser = this.scoreUserService.selectOne(new EntityWrapper<ScoreUser>().eq("user_id", productOrder.getUserId()));
        if (Objects.equals(product.getNeedPoints().compareTo(scoreUser.getResiduceScore()), 1)) {
            return this.productKindService.judge("您当前的积分不够兑换当前商品，请继续努力！");
        }
        //用户下单
        this.productOrderService.setOrder(productOrder, product.getNeedPoints());
        return response;
    }

    /**
     * 设置发货时间/快递单号
     */
    @PostMapping("/updateTime")
    public Response updateTime(@RequestBody ProductOrder productOrder) {
        if (Objects.isNull(productOrder.getId()) || Objects.isNull(productOrder.getDeliveryTime()) || Objects.isNull(productOrder.getTrackingNumber())) {
            return this.productKindService.judge("请填写必要信息!");
        }
        Response response = new Response();
        this.productOrderService.updateTime(productOrder);
        return response;
    }

    /**
     * 用户确认收货，上传收获时间，修改订单状态
     * */
    @PostMapping("/gainGoods")
    public Response gainGoods(@RequestBody ProductOrder productOrder){
        if (Objects.isNull(productOrder.getId()) || Objects.isNull(productOrder.getRecvingTime())) {
            return this.productKindService.judge("请填写必要信息!");
        }
        Response response = new Response();
        this.productOrderService.gainGoods(productOrder);
        return response;
    }

}

