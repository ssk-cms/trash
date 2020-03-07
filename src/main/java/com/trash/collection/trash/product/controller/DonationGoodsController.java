package com.trash.collection.trash.product.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.VO.DonationGoodsVO;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.trash.collection.trash.product.service.DonationGoodsService;
import com.trash.collection.trash.product.service.ProductKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 控制器
 *
 * @author seth
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/product/donationGoods")
public class DonationGoodsController {

    @Autowired
    private DonationGoodsService goodsService;

    @Autowired
    ProductKindService kindService;

    /**
     * 获取捐赠物品列表
     */
    @GetMapping("/getDonationGoodsList")
    public Response getDonationGoodsList(@Validated DonationGoodsVO goodsVO) {
        Response response = new Response();
        response.setData(goodsService.getDonationGoodsList(goodsVO));
        return response;
    }

    /**
     * 更新捐赠物品积分
     */
    @PostMapping("/setGoodsScore")
    public Response setGoodsScore(@RequestBody DonationGoods donationGoods) {
        Response response = new Response();
        if (Objects.isNull(donationGoods.getId()) || Objects.isNull(donationGoods.getAcquireScore()) || Objects.isNull(donationGoods.getUserId())) {
            return kindService.judgeParam();
        }
        goodsService.setGoodsScore(donationGoods);
        return response;
    }
}

