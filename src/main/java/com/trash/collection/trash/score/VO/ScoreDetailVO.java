package com.trash.collection.trash.score.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Seth
 * @Date: 2020/4/19 15:21
 * @Version 1.0
 * 积分详情VO
 */
@Data
@Accessors(chain = true)
public class ScoreDetailVO {

    /**
     * 积分明细id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 捐赠物品订单id
     */
    private Long donationGoodsOrderId;
    /**
     * 商品兑换订单id
     */
    private Long productOrderId;

    /**
     * 增减积分
     */
    private BigDecimal score;

    /**
     * 商品名称
     * */
    private String productName;

    /**
     * 捐赠物品名称
     * */
    private String goodsName;
    /**
     * 详情状态【1、捐赠物品订单；2、积分兑换订单】
     */
    @TableField("state")
    private Integer state;
    /**
     * 积分详情时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;
}
