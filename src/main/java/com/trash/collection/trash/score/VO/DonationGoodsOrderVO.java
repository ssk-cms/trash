package com.trash.collection.trash.score.VO;

import com.trash.collection.trash.product.VO.PageVO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Seth
 * @Date: 2020/3/10 19:37
 * @Version 1.0
 * 捐赠物品订单信息vo
 */
@Data
@Accessors(chain = true)
public class DonationGoodsOrderVO extends PageVO implements Serializable {

    /**
     * 筛选参数
     * */
    private String param;
    /**
     * 捐赠物品名称
     * */
    private String goodsName;

    /**
     * 捐赠物品id
     * */
    private Integer donationGoodsId;

    /**
     *订单状态
     * */
    private Integer state;

    /**
     * 订单号
     * */
    private String orderNumber;

    /**
     * 下单人姓名
     * */
    private String userName;

    /**
     *上门取货工作人员名称
     * */
    private String workerName;

    /**
     *捐赠商品数量
     * */
    private Integer goodsCount;

    /**
     *捐赠商品获得的积分
     * */
    private BigDecimal gainScore;

    /**
     * 获得积分的时间
     * */
    private Date gainScoreTime;
}
