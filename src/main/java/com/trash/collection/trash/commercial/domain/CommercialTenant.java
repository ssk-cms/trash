package com.trash.collection.trash.commercial.domain;

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

/**
 * 商户加盟表
 *
 * @author Seth
 * @since 2020-05-04
 */
@Data
@Accessors(chain = true)
@TableName("ac_commercial_tenant")
public class CommercialTenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商户名称
     */
    private String name;
    /**
     * 商户老板姓名
     */
    @TableField("boss_name")
    private String bossName;
    /**
     * 商户电话
     */
    private String phone;
    /**
     * 商户地址
     */
    private String address;
    /**
     * 回收物品类型介绍
     */
    @TableField("shop_introduce")
    private String shopIntroduce;
    /**
     * 商户图片
     */
    private String images;
    /**
     * 信誉积分
     */
    @TableField("reputation_score")
    private BigDecimal reputationScore;
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
