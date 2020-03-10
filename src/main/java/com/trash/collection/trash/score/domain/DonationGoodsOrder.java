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
@TableName("sc_donation_goods_order")
public class DonationGoodsOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 捐赠物品订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 捐赠物品id
     */
    @TableField("donation_goods_id")
    private Long donationGoodsId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 捐赠物品物流信息id
     */
    @TableField("donation_logistics_id")
    private Long donationLogisticsId;
    /**
     * 上门工作人员id
     * */
    @TableField("worker_message_id")
    private Long workerMessageId;
    /**
     * 用户地址id
     * */
    @TableField("user_address_id")
    private Long userAddressId;
    /**
     * 捐赠物品订单号
     */
    @TableField("goods_order_number")
    private String goodsOrderNumber;
    /**
     * 捐赠商品数量
     */
    @TableField("goods_count")
    private Integer goodsCount;
    /**
     * 捐赠商品后获得的积分
     */
    @TableField("gain_score")
    private BigDecimal gainScore;
    /**
     * 获得积分的时间
     */
    @TableField("gain_score_time")
    private Date gainScoreTime;
    /**
     * 订单状态【0、失效；1、正常；2、已完成】
     */
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
