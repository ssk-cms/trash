package com.trash.collection.trash.product.domain;

import java.util.Date;
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
@TableName("pr_product_kind")
public class ProductKind implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * 商品种类id
     */
    private Long id;
    /**
     * 商品种类名称
     */
    @TableField("product_name")
    private String productName;
    /**
     * 商品种类类型[1、积分兑换商品种类，2、捐赠商品种类]
     */
    @TableField("product_kind_type")
    private Integer productKindType;
    /**
     * 商品种类状态【1、在用，0、归档】
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
