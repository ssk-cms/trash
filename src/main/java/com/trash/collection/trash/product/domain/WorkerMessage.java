package com.trash.collection.trash.product.domain;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author seth
 * @since 2020-03-08
 */
@Data
@Accessors(chain = true)
@TableName("pr_worker_message")
public class WorkerMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上门回收物品工作人员信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 工作人员姓名
     */
    private String name;
    /**
     * 工作人员电话
     */
    @TableField("phone_number")
    private String phoneNumber;
    /**
     * 工作人员身份证号
     */
    @TableField("id_card")
    private String idCard;
    /**
     * 工作人员照片
     */
    private String images;
    /**
     * 工作人员状态【0、离职；1、空闲；2、接单中】
     * */
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
