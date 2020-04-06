package com.trash.collection.trash.activity.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

//    活动正常状态
    public static final Integer NORMAL_STATE = 1;
//    活动禁止状态
    public static final Integer FORBIDDEN_STATE = 0;
    /**
     * 活动id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 活动标题
     */
    @NotNull(message = "活动标题不能为空")
    @NotEmpty(message = "活动标题不能为空")
    @TableField("activity_title")
    private String activityTitle;
    /**
     * 活动内容
     */
    @TableField("activity_content")
    @NotNull(message = "活动内容不能为空")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date modifyTime;

}
