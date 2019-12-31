package com.trash.collection.trash.user.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户与组织机构对应关系
 *
 * @author seth
 * @since 2019-12-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_org")
public class UserOrg implements Serializable{

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户编号
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 组织机构编号
     */
    @TableField("org_id")
    private Integer orgId;
    /**
     * 级别，10：普通员工，15：主管，20：部门负责人，30：副总经理，40：总经理
     */
    private String level;

}
