package com.trash.collection.trash.product.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;


import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author seth
 * @since 2020-01-19
 */
@Data
@Accessors(chain = true)
@TableName("pr_product_kind")
public class ProductKind implements Serializable {

    private static final long serialVersionUID = 1L;
    //    在用商品种类状态
    public static final Integer NORMAL_STATE = 1;
    //    归档商品种类状态
    public static final Integer FORBIDDEN_STATE = 0;

    /**
     * 商品种类id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品种类名称
     */
    @TableField("product_name")
    @NotNull(message = "请填写商品种类名称")
    private String productName;
    /**
     * 商品种类类型[1、积分兑换商品种类，2、捐赠商品种类]
     */
    @TableField("product_kind_type")
    @NotNull(message = "请选择物品类型（捐赠物品或积分兑换商品）")
    private Integer productKindType;
    /**
     * 商品种类状态【1、在用，0、归档】
     */
    private Integer state;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date modifyTime;

}
