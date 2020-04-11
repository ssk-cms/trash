package com.trash.collection.trash.product.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品展示列表
 * */
@Data
@Accessors(chain = true)
public class ProductVO {
    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品种类id
     */
    private Long productKindId;

    /**
     * 商品种类名称
     */
    private String productKindName;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片地址
     */
    private String productImages;
    /**
     * 商品原价
     */
    private BigDecimal commodityPrice;
    /**
     * 商品现价
     */
    private BigDecimal productPrice;
    /**
     * 兑换所需积分
     */
    private BigDecimal needPoints;
    /**
     * 商品总数
     */
    private Integer totalNumber;
    /**
     * 商品库存
     */
    private Integer stockNumber;
    /**
     * 商品上架时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date productPutawayTime;
    /**
     * 商品下架时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date productSoldOutTime;
    /**
     * 商品状态【1、在用，0、下架】
     */
    private Integer state;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date modifyTime;
}
