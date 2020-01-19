package com.trash.collection.trash.activity.domain;

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
@TableName("ac_activity_msg")
public class ActivityMsg implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * 活动id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 活动标题
     */
    @TableField("activity_title")
    private String activityTitle;
    /**
     * 活动内容
     */
    @TableField("activity_content")
    private String activityContent;
    /**
     * 活动图片地址
     */
    @TableField("activity_images")
    private String activityImages;
    /**
     * 活动时间
     */
    @TableField("activity_time")
    private Date activityTime;
    /**
     * 友情链接
     */
    private String blogroll;
    /**
     * 创建人id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 活动状态，【0、禁止，1、在用】
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
