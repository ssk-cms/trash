package com.trash.collection.trash.score.VO;

import com.trash.collection.trash.product.VO.PageVO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Seth
 * @Date: 2020/3/11 15:12
 * @Version 1.0
 * 积分兑换商品订单VO
 */
@Data
@Accessors(chain = true)
public class ProductOrderVO extends PageVO implements Serializable {

    /**
     * 兑换订单主键id
     */
    private Long id;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户地址id
     * */
    private Long userAddressId;
    /**
     * 商品数量
     */
    private Integer productCount;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 收货时间
     */
    private Date recvingTime;
    /**
     * 消费积分
     */
    private BigDecimal expendScore;
    /**
     * 快递单号
     */
    private String trackingNumber;
    /**
     * 订单状态【1、在用，2、无效，3、已完成】
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 商品名称
     * */
    private String productName;
    /**
     * 下单用户名称
     * */
    private String userName;
    /**
     * 传参名称
     * */
    private String param;
}
