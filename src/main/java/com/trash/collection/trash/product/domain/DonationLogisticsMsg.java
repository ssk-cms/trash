package com.trash.collection.trash.product.domain;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author seth
 * @since 2020-02-26
 */
@Data
@Accessors(chain = true)
@TableName("pr_donation_logistics_msg")
public class DonationLogisticsMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 捐赠物品物流信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单id
     */
    @TableField("donation_goods_order_id")
    private Long donationGoodsOrderId;
    /**
     * 捐赠物品id
     */
    @TableField("donate_goods_id")
    private Long donateGoodsId;
    /**
     * 上门回收工作人员id
     * */
    @TableField("worker_message_id")
    private Long workerMessageId;
    /**
     * 物流信息标题
     */
    @TableField("logistics_msg_title")
    private String logisticsMsgTitle;
    /**
     * 物流信息内容
     */
    @TableField("logistics_msg_content")
    private String logisticsMsgContent;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date modifyTime;


}
