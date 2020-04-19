package com.trash.collection.trash.address.domain;

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
 * 
 *
 * @author seth
 * @since 2020-01-19
 */
@Data
@Accessors(chain = true)
@TableName("us_user_address")
public class UserAddress implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * 地址id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 收货/发货姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 邮编
     */
    private String postcode;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 地址类型【1、收获地址，2、发货地址】
     */
    @TableField("address_type")
    private Integer addressType;
    /**
     * 地址状态【1、在用，0、归档】
     */
    private Integer state;
    /**
     * 是否为默认地址【1、是，0不是】
     */
    @TableField("default_address_state")
    private Integer defaultAddressState;
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
