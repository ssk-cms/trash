package com.trash.collection.trash.score.controller;


import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.score.VO.ProductOrderVO;
import com.trash.collection.trash.score.domain.ProductOrder;
import com.trash.collection.trash.score.service.ProductOrderService;
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

    @GetMapping("/list")
    public Response list(ProductOrderVO orderVO){
        Response response = new Response();
        response.setData(productOrderService.getOrderList(orderVO));
        return response;
    }

    /**
     * a填写快递单号
     * */
    @PostMapping("/updateTrackingNumber")
    public Response updateTrackingNumber(@RequestBody ProductOrder productOrder){
        Response response = new Response();
        if (Objects.isNull(productOrder.getId())||Objects.isNull(productOrder.getTrackingNumber())){
            return productKindService.judgeParam();
        }
        productOrderService.updateTrackingNumber(productOrder);
        return response;
    }

}

