package com.trash.collection.trash.product.domain;

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
 * 
 *
 * @author seth
 * @since 2020-01-19
 */
@Data
@Accessors(chain = true)
@TableName("pr_product")
public class Product implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品种类id
     */
    @TableField("product_kind_id")
    private Long productKindId;
    /**
     * 商品名称
     */
    @TableField("product_name")
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
    private Integer needPoints;
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
     * 商品类型【1、积分兑换商品，2、捐赠物品商品】
     */
    @TableField("product_type")
    private Integer productType;
    /**
     * 商品上架时间
     */
    @TableField("product_putaway_time")
    private Date productPutawayTime;
    /**
     * 商品下架时间
     */
    @TableField("product_sold_out_time")
    private Date productSoldOutTime;
    /**
     * 商品状态【1、在用，0、归档】
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
