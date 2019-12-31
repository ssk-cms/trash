package com.trash.collection.trash.user.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色
 *
 * @author seth
 * @since 2019-12-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_role")
public class Role implements Serializable{

    private static final long serialVersionUID=1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人id
     */
    @TableField("create_user_id")
    private Integer createUserId;
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
