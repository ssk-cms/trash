package com.trash.collection.trash.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.VO.DonationGoodsVO;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.trash.collection.trash.product.dao.DonationGoodsMapper;
import com.trash.collection.trash.product.service.DonationGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.trash.collection.trash.score.domain.DonationGoodsOrder;
import com.trash.collection.trash.score.domain.ScoreUser;
import com.trash.collection.trash.score.service.DonationGoodsOrderService;
import com.trash.collection.trash.score.service.ScoreUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-02-26
 */
@Service
public class DonationGoodsServiceImpl extends ServiceImpl<DonationGoodsMapper, DonationGoods> implements DonationGoodsService {

    @Autowired
    private DonationGoodsMapper goodsMapper;

    @Autowired
    private DonationGoodsService goodsService;

    @Autowired
    private ScoreUserService scoreUserService;

    @Autowired
    private DonationGoodsOrderService goodsOrderService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取捐赠信息列表
     */
    @Override
    public Page<DonationGoods> getDonationGoodsList(DonationGoodsVO goodsVO) {
        Page<DonationGoods> page = new Page<>(goodsVO.getPageIndex(), goodsVO.getPageSize());
        page.setRecords(goodsMapper.getDonationGoodsList(page,goodsVO.getState(),goodsVO.getGoodsName(),goodsVO.getProductKindId()));
        return page;
    }

    /**
     * 更新捐赠物品积分
     * */
    @Override
    @Transactional
    public void setGoodsScore(DonationGoods donationGoods){
        Date date = new Date();
        DonationGoods goods = new DonationGoods();
        goods.setId(donationGoods.getId())
                .setAcquireScore(donationGoods.getAcquireScore())
                .setModifyTime(date)
                .setState(2);
        goodsService.updateById(goods);
        //更新用户个人总积分
        this.updateUserScore(donationGoods);
        //更新捐赠物品订单中的获取积分
        this.updateOrderScore(donationGoods);

    }

    /**
     * 更新用户总积分
     * */
    private void updateUserScore(DonationGoods donationGoods) {
        Date date = new Date();
        ScoreUser scoreUser = scoreUserService.selectOne(new EntityWrapper<ScoreUser>().eq("user_id",donationGoods.getUserId()));
        BigDecimal totalscore = scoreUser.getTotalScore().add(donationGoods.getAcquireScore());
        ScoreUser score = new ScoreUser();
        score.setModifyTime(date)
                .setTotalScore(totalscore);
        scoreUserService.update(score,new EntityWrapper<ScoreUser>().eq("user_id",donationGoods.getUserId()));
    }

    /**
     * 更新捐赠物品订单中的获取积分数和获取积分的时间
     * */
    private void updateOrderScore(DonationGoods donationGoods) {
        DonationGoodsOrder goodsOrder = new DonationGoodsOrder();
        //判断用户有没有下单
        DonationGoodsOrder order = goodsOrderService.selectOne(new EntityWrapper<DonationGoodsOrder>().eq("donation_goods_id",donationGoods.getId()));
        if (Objects.isNull(order)){
            logger.info("当前用户没有捐赠物品订单，不能更新捐赠物品订单中的积分！");
            return;
        }
        goodsOrder.setGainScore(donationGoods.getAcquireScore())
                .setGainScoreTime(new Date());
        goodsOrderService.update(goodsOrder,new EntityWrapper<DonationGoodsOrder>().eq("donation_goods_id",donationGoods.getId()));
    }
}