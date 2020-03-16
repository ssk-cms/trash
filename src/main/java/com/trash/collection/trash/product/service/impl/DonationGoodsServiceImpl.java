package com.trash.collection.trash.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.VO.DonationGoodsVO;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.trash.collection.trash.product.dao.DonationGoodsMapper;
import com.trash.collection.trash.product.domain.DonationLogisticsMsg;
import com.trash.collection.trash.product.domain.WorkerMessage;
import com.trash.collection.trash.product.service.DonationGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.trash.collection.trash.product.service.DonationLogisticsMsgService;
import com.trash.collection.trash.product.service.WorkerMessageService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-02-26
 */
@Service
public class DonationGoodsServiceImpl extends ServiceImpl<DonationGoodsMapper, DonationGoods> implements DonationGoodsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DonationGoodsMapper goodsMapper;

    @Autowired
    private DonationGoodsService goodsService;

    @Autowired
    private ScoreUserService scoreUserService;

    @Autowired
    private DonationGoodsOrderService goodsOrderService;

    @Autowired
    private DonationLogisticsMsgService logisticsMsgService;

    @Autowired
    private WorkerMessageService workerMessageService;

    /**
     * 获取捐赠信息列表
     */
    @Override
    public Page<DonationGoods> getDonationGoodsList(DonationGoodsVO goodsVO) {
        Page<DonationGoods> page = new Page<>(goodsVO.getPageIndex(), goodsVO.getPageSize());
        page.setRecords(goodsMapper.getDonationGoodsList(page, goodsVO.getState(), goodsVO.getGoodsName(), goodsVO.getProductKindId()));
        return page;
    }

    /**
     * 更新捐赠物品积分
     */
    @Override
    @Transactional
    public void setGoodsScore(DonationGoods donationGoods) {
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
     */
    private void updateUserScore(DonationGoods donationGoods) {
        Date date = new Date();
        ScoreUser scoreUser = scoreUserService.selectOne(new EntityWrapper<ScoreUser>().eq("user_id", donationGoods.getUserId()));
        BigDecimal totalscore = scoreUser.getTotalScore().add(donationGoods.getAcquireScore());
        ScoreUser score = new ScoreUser();
        score.setModifyTime(date)
                .setTotalScore(totalscore);
        scoreUserService.update(score, new EntityWrapper<ScoreUser>().eq("user_id", donationGoods.getUserId()));
    }

    /**
     * 更新捐赠物品订单中的获取积分数和获取积分的时间
     */
    private void updateOrderScore(DonationGoods donationGoods) {
        DonationGoodsOrder goodsOrder = new DonationGoodsOrder();
        //判断用户有没有下单
        DonationGoodsOrder order = goodsOrderService.selectOne(new EntityWrapper<DonationGoodsOrder>().eq("donation_goods_id", donationGoods.getId()));
        if (Objects.isNull(order)) {
            logger.info("当前用户没有捐赠物品订单，不能更新捐赠物品订单中的积分！");
            return;
        }
        goodsOrder.setGainScore(donationGoods.getAcquireScore())
                .setGainScoreTime(new Date());
        goodsOrderService.update(goodsOrder, new EntityWrapper<DonationGoodsOrder>().eq("donation_goods_id", donationGoods.getId()));
    }

    /**
     * 更新捐赠物品物流状态--安排工作人员上门
     */
    @Override
    @Transactional
    public void arrangeWorrker(DonationGoods donationGoods) {
        //更新捐赠物品中的捐赠物品状态
        DonationGoods goods = new DonationGoods().setId(donationGoods.getId())
                .setLogisticsStatus(21)
                .setModifyTime(new Date());
        goodsService.updateById(goods);
        //设置捐赠物品的详细物流信息
        Map<String, String> map = new HashMap<>();
        map.put("title", "已安排工作人员上门");
        map.put("content", "已安排工作人员按照您预定的时间上门回收您捐赠的物品，如需查看工作人员信息，请在捐赠订单中查看！");
        this.setlogisticsMsg(donationGoods, map);
    }

    /**
     * 设置捐赠物品的物流信息
     */
    private void setlogisticsMsg(DonationGoods goodsOrder, Map<String, String> map) {
        Date date = new Date();
        DonationLogisticsMsg logisticsMsg = new DonationLogisticsMsg();
        logisticsMsg.setDonateGoodsId(goodsOrder.getId())
                .setLogisticsMsgTitle(map.get("title"))
                .setLogisticsMsgContent(map.get("content"))
                .setCreateTime(date)
                .setModifyTime(date);
        logisticsMsgService.insert(logisticsMsg);
    }

    /**
     * 更新捐赠物品物流状态--商品入库
     */
    @Override
    @Transactional
    public void putInStorage(DonationGoods donationGoods) {
        //更新捐赠物品中的物流状态
        DonationGoods goods = new DonationGoods().setModifyTime(new Date())
                .setId(donationGoods.getId())
                .setLogisticsStatus(30);
        this.goodsService.updateById(goods);
        //更新物流信息中的物流状态
        Map<String, String> map = new HashMap<>();
        map.put("title", "商品已入库");
        map.put("content", "商品已入库，等待有需要的人出现，会送至指定目的地！");
        this.setlogisticsMsg(donationGoods, map);
        //更新工作人员状态
        this.setWorkerStatus(donationGoods);
    }

    /**
     * 更新工作人员状态
     */
    private void setWorkerStatus(DonationGoods donationGoods) {
        //查询该捐赠物品的捐款订单信息
        DonationGoodsOrder goodsOrder = this.goodsOrderService.selectOne(
                new EntityWrapper<DonationGoodsOrder>().eq("donationGoodsId", donationGoods.getId()));
        if (Objects.isNull(goodsOrder)){
            logger.info("该捐赠物品没有相关捐赠订单！请核对！");
            return;
        }
        if (Objects.isNull(goodsOrder.getWorkerMessageId())){
            logger.info("该订单没有工作人员接单，请核实");
        }
        WorkerMessage workerMessage = new WorkerMessage().setId(goodsOrder.getWorkerMessageId())
                .setState(1)
                .setModifyTime(new Date());
        this.workerMessageService.updateById(workerMessage);
    }

    @Override
    @Transactional
    public void stockRemove(DonationGoods donationGoods){
        //更新物流状态
        DonationGoods goods = new DonationGoods().setId(donationGoods.getId())
                .setLogisticsStatus(donationGoods.getLogisticsStatus())
                .setModifyTime(new Date());
        this.goodsService.updateById(goods);
        //更新物流信息
        Map<String, String> map = new HashMap<>();
        if (Objects.equals(donationGoods.getLogisticsStatus(),40)){
            map.put("title", "商品已出库");
        }else if (Objects.equals(donationGoods.getLogisticsStatus(),50)){
            map.put("title", "商品已送至目的地");
        }
        if (Objects.nonNull(donationGoods.getRemark())){
            map.put("content", donationGoods.getRemark());
        }
        this.setlogisticsMsg(donationGoods, map);
    }
}