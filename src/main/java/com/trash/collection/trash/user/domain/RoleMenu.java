package com.trash.collection.trash.user.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色与菜单对应关系
 *
 * @author seth
 * @since 2019-12-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_menu")
public class RoleMenu implements Serializable{

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Integer menuId;

}
