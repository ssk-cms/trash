package com.trash.collection.trash.product.domain;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author seth
 * @since 2020-01-19
 */
@Data
@Accessors(chain = true)
@TableName("pr_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    //    正常的商品状态
    public static final Integer NORMAL_STATE = 1;
    //    归档的商品状态
    public static final Integer FORBIDEN_STATE = 0;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品种类id
     */
    @TableField("product_kind_id")
    @NotNull(message = "请选择商品种类")
    @NotEmpty(message = "请选择商品种类")
    private Long productKindId;
    /**
     * 商品名称
     */
    @TableField("product_name")
    @NotNull(message = "请填写商品名称")
    private String productName;
    /**
     * 商品图片地址
     */
    @TableField("product_images")
    private String productImages;
    /**
     * 商品原价
     */
    @TableField("commodity_price")
    private BigDecimal commodityPrice;
    /**
     * 商品现价
     */
    @TableField("product_price")
    private BigDecimal productPrice;
    /**
     * 兑换所需积分
     */
    @TableField("need_points")
    private BigDecimal needPoints;
    /**
     * 商品总数
     */
    @TableField("total_number")
    private Integer totalNumber;
    /**
     * 商品库存
     */
    @TableField("stock_number")
    private Integer stockNumber;
    /**
     * 商品上架时间
     */
    @TableField("product_putaway_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date productPutawayTime;
    /**
     * 商品下架时间
     */
    @TableField("product_sold_out_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date productSoldOutTime;
    /**
         * 商品状态【1、在用，0、下架】
     */
    private Integer state;
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
