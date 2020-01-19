package com.trash.collection.trash.score.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author seth
 * @since 2020-01-19
 */
@Data
@Accessors(chain = true)
@TableName("sc_order")
public class Order implements Serializable{

private static final long serialVersionUID=1L;

    /**
     * 订单主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    @TableField("order_number")
    private String orderNumber;
    /**
     * 商品id
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 商品数量
     */
    @TableField("product_count")
    private Integer productCount;
    /**
     * 发货时间
     */
    @TableField("delivery_time")
    private Date deliveryTime;
    /**
     * 收获时间
     */
    @TableField("recving_time")
    private Date recvingTime;
    /**
     * 下单时间
     */
    @TableField("create_order_time")
    private Date createOrderTime;
    /**
     * 快递单号
     */
    @TableField("tracking_number")
    private String trackingNumber;
    /**
     * 下单类型【1、积分兑换订单，2、捐赠物品订单】
     */
    @TableField("order_type")
    private Integer orderType;
    /**
     * 订单状态【1、在用，2、无效，3、已完成】
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
