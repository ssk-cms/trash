package com.trash.collection.trash.score.controller;


import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.score.domain.ProductOrder;
import com.trash.collection.trash.score.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/list")
    public Response list(){
        Response response = new Response();

        return response;
    }

}

