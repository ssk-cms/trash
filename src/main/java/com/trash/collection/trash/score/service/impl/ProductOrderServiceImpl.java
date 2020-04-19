package com.trash.collection.trash.score.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.common.NotLoginedDotGo;
import com.trash.collection.trash.score.VO.ProductOrderVO;
import com.trash.collection.trash.score.domain.ProductOrder;
import com.trash.collection.trash.score.dao.ProductOrderMapper;
import com.trash.collection.trash.score.domain.ScoreDetail;
import com.trash.collection.trash.score.domain.ScoreUser;
import com.trash.collection.trash.score.service.ProductOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.trash.collection.trash.score.service.ScoreDetailService;
import com.trash.collection.trash.score.service.ScoreUserService;
import com.trash.collection.trash.user.VO.UserInfo;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-03-07
 */
@Service
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder> implements ProductOrderService {

    @Autowired
    ProductOrderMapper orderMapper;

    @Autowired
    ScoreUserService scoreUserService;

    @Autowired
    private ScoreDetailService scoreDetailService;

    UserInfo userInfo = NotLoginedDotGo.getUser();

    /**
     * 积分兑换商品订单列表
     * */
    @Override
    public Page<ProductOrderVO> getOrderList(ProductOrderVO orderVO){
        Page<ProductOrderVO> page = new Page<>(orderVO.getPageIndex(),orderVO.getPageSize());
        page.setRecords(orderMapper.getOrderList(page,orderVO.getParam(),orderVO.getState(),orderVO.getOrderNumber()));
        return page;
    }

    /**
     * 填写快递单号
     * */
    @Override
    @Transactional
    public void updateTrackingNumber(ProductOrder productOrder){
        ProductOrder order = new ProductOrder();
        order.setId(productOrder.getId())
                .setTrackingNumber(productOrder.getTrackingNumber())
                .setModifyTime(new Date());
        this.orderMapper.updateById(order);
    }

    /**
     * 用户下单
     * */
    @Override
    @Transactional
    public void setOrder(ProductOrder productOrder, BigDecimal needPoints){
        //用户下单
        Date date = new Date();
        productOrder.setOrderNumber(String.format("%1$s%2$s",
                new SimpleDateFormat("yyyyMMddHHmmss").format(date), RandomStringUtils.randomNumeric(6)));
        productOrder.setExpendScore(needPoints);
        productOrder.setState(1);
        productOrder.setCreateTime(date);
        productOrder.setModifyTime(date);
        this.baseMapper.insert(productOrder);
        //用户剩余积分减去相应积分
        this.updateUserScore(productOrder,needPoints);
        //记录积分详情
        this.setUserScoreDetail(userInfo.getId(),needPoints,productOrder.getId());

    }

    /**
     * 记录用户积分详情
     * */
    private void setUserScoreDetail(Long userId, BigDecimal needPoints, Long productOrderId) {
        ScoreDetail scoreDetail = new ScoreDetail();
        Date date = new Date();
        scoreDetail.setUserId(userId);
        scoreDetail.setScore(needPoints);
        scoreDetail.setProductOrderId(productOrderId);
        scoreDetail.setCreateTime(date);
        scoreDetail.setModifyTime(date);
        scoreDetailService.insert(scoreDetail);
    }

    /**
     * 更新发货时间-快递单号
     * */
    @Override
    @Transactional
    public void updateTime(ProductOrder productOrder){
        productOrder.setModifyTime(new Date())
                .setState(4);
        this.baseMapper.updateById(productOrder);
    }

    /**
     * 更新用户剩余积分
     * */
    private void updateUserScore(ProductOrder productOrder, BigDecimal needPoints){
        ScoreUser scoreUser = this.scoreUserService.selectOne(new EntityWrapper<ScoreUser>().eq("user_id",productOrder.getUserId()));
        BigDecimal resdiuceScore = scoreUser.getResiduceScore().subtract(needPoints);
        ScoreUser scoreUser1 = new ScoreUser();
        scoreUser1.setId(scoreUser.getId())
                .setResiduceScore(resdiuceScore)
                .setModifyTime(new Date());
        this.scoreUserService.updateById(scoreUser1);
    }

    /**
     * 更新收货时间，变更订单状态
     * */
    @Override
    @Transactional
    public void gainGoods(ProductOrder productOrder){
        productOrder.setState(3)
                .setModifyTime(new Date());
        this.baseMapper.updateById(productOrder);
    }

    /**
     * 用户查看自己的订单列表
     * */
    @Override
    public Page<ProductOrder> getListByUser(Page<ProductOrder> page, Integer userId, Integer state){
        return page.setRecords(this.baseMapper.getListByUser(page,userId,state));
    }
}
