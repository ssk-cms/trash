package com.trash.collection.trash.product.controller;


import com.trash.collection.trash.common.NotLoginedDotGo;
import com.trash.collection.trash.common.RRException;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.VO.DonationGoodsVO;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.trash.collection.trash.product.domain.DonationLogisticsMsg;
import com.trash.collection.trash.product.service.DonationGoodsService;
import com.trash.collection.trash.product.service.DonationLogisticsMsgService;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.user.VO.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 控制器
 *
 * @author seth
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/product/donationGoods")
@CrossOrigin
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
        if (Objects.isNull(donationGoods.getId()) || Objects.isNull(donationGoods.getAcquireScore())) {
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
        UserInfo userInfo = NotLoginedDotGo.getUser();
        if (Objects.isNull(userInfo.getId())){
            return productKindService.judgeParam();
        }
        donationGoodsVO.setUserId(userInfo.getId());
        Response response = new Response();
        response.setData(goodsService.getListByUser(donationGoodsVO));
        return response;
    }

    /**
     * 用户查看捐赠物品的物流信息
     * */
    @GetMapping("/getLogistics")
    public Response getLogistics(Long donationsGoodsId){
        if (Objects.isNull(donationsGoodsId)){
            return productKindService.judgeParam();
        }
        Response response = new Response();
        List<DonationLogisticsMsg> logisticsMsgs = logisticsMsgService.selectByDonationsGoodsId(donationsGoodsId);
        if (CollectionUtils.isEmpty(logisticsMsgs)){
            response.setMessage("该物品暂无物流信息");
            return response;
        }
        response.setData(logisticsMsgs);
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
        if (Objects.isNull(donationGoods.getGoodsName()) || Objects.equals(donationGoods.getGoodsName(),"")){
            throw new RRException("请填写捐赠物品名称");
        }
        if (Objects.equals(donationGoods.getDonationCount(),0)){
            throw new RRException("捐赠物品数量不能为零！");
        }
        UserInfo userInfo = NotLoginedDotGo.getUser();
        donationGoods.setUserId(userInfo.getId());
        Response response = new Response();
        this.goodsService.add(donationGoods);
        return response;
    }


}

