package com.trash.collection.trash.user.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * 菜单管理
 *
 * @author seth
 * @since 2019-12-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu implements Serializable{

private static final long serialVersionUID=1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;
    /**
     * 父菜单ID，一级菜单为0
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;
    /**
     * 创建人id
     */
    @TableField("create_user_id")
    private Integer createUserId;
    /**
     * 创建日期
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

}
