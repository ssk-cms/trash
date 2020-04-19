package com.trash.collection.trash.score.domain;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author seth
 * @since 2020-03-07
 */
@Data
@Accessors(chain = true)
@TableName("sc_score_detail")
public class ScoreDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 积分明细id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 增减积分
     */
    @TableField("score")
    private BigDecimal score;
    /**
     * 捐赠物品订单id
     */
    @TableField("donation_goods_order_id")
    private Long donationGoodsOrderId;
    /**
     * 商品兑换订单id
     */
    @TableField("product_order_id")
    private Long productOrderId;
    /**
     * 详情状态【1、捐赠物品订单；2、积分兑换订单】
     */
    @TableField("state")
    private Integer state;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;


}
