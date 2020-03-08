package com.trash.collection.trash.product.controller;


import com.trash.collection.trash.product.service.DonationLogisticsMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 *  控制器
 *
 * @author seth
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/product/donationLogisticsMsg")
public class DonationLogisticsMsgController {

    @Autowired
    private DonationLogisticsMsgService logisticsMsgService;

}

