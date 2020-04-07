package com.trash.collection.trash.product.controller;


import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.VO.DonationGoodsVO;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.trash.collection.trash.product.service.DonationGoodsService;
import com.trash.collection.trash.product.service.DonationLogisticsMsgService;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.score.domain.DonationGoodsOrder;
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
    private ProductKindService kindService;

    @Autowired
    private ProductKindService productKindService;

    @Autowired
    private DonationLogisticsMsgService logisticsMsgService;

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
        DonationGoods result = this.goodsService.selectById(donationGoods.getId());
        if (Objects.nonNull(result.getAcquireScore())){
            return this.kindService.judge("已为该捐赠商品设置积分，无需重复设置用户积分");
        }
        goodsService.setGoodsScore(donationGoods);
        return response;
    }

    /**
     * 更新捐赠物品状态-已派送工作人员上门
     */
    @PostMapping("/arrangeWorrker")
    public Response arrangeWorrker(@RequestBody DonationGoods donationGoods) {
        if (Objects.isNull(donationGoods.getId())) {
            return productKindService.judgeParam();
        }
        Response response = new Response();
        goodsService.arrangeWorrker(donationGoods);
        return response;
    }

    /**
     * 更新捐赠物品状态--捐赠物品已入库
     */
    @PostMapping("/putInStorage")
    public Response putInStorage(@RequestBody DonationGoods donationGoods) {
        if (Objects.isNull(donationGoods.getId())) {
            return productKindService.judgeParam();
        }
        Response response = new Response();
        goodsService.putInStorage(donationGoods);
        return response;
    }

    /**
     * 更新捐赠物品状态--商品已出库
     * 更新捐赠物品状态--商品已送至目的地
     */
    @PostMapping("/stockRemove")
    public Response stockRemove(@RequestBody DonationGoods donationGoods) {
        if (Objects.isNull(donationGoods)) {
            return productKindService.judgeParam();
        }
        if (Objects.isNull(donationGoods.getId()) || Objects.isNull(donationGoods.getLogisticsStatus())) {
            return productKindService.judgeParam();
        }
        Response response = new Response();
        this.goodsService.stockRemove(donationGoods);
        return response;
    }

    /**
     * 用户查看自己捐赠的物品信息
     * */
    @GetMapping("/getListByUser")
    public Response getListByUser(DonationGoodsVO donationGoodsVO){
        if (Objects.isNull(donationGoodsVO)){
            return productKindService.judgeParam();
        }
        if (Objects.isNull(donationGoodsVO.getUserId())){
            return productKindService.judgeParam();
        }
        Response response = new Response();
        response.setData(goodsService.getListByUser(donationGoodsVO));
        return response;
    }

    /**
     * 用户查看捐赠物品的物流信息
     * */
    @GetMapping("/getLogistics")
    public Response getLogistics(Long logisticsId){
        if (Objects.isNull(logisticsId)){
            return productKindService.judgeParam();
        }
        Response response = new Response();
        response.setData(logisticsMsgService.selectById(logisticsId));
        return response;
    }

    /**
     * 用户填写捐赠物品相关信息
     * */
    @PostMapping("/add")
    public Response add(@RequestBody DonationGoods donationGoods){
        if (Objects.isNull(donationGoods)){
            return this.productKindService.judgeParam();
        }
        Response response = new Response();
        this.goodsService.add(donationGoods);
        return response;
    }


}

