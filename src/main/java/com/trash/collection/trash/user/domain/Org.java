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
 * 组织架构
 *
 * @author seth
 * @since 2019-12-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_org")
public class Org implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * 组织架构编号
     */
    @TableId(value = "org_id", type = IdType.AUTO)
    private Integer orgId;
    /**
     * 机构名称
     */
    @TableField("org_name")
    private String orgName;
    /**
     * 机构简称
     */
    private String alias;
    /**
     * 区域编码
     */
    @TableField("area_code")
    private String areaCode;
    /**
     * 父机构编号
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 机构类型[10：公司，20：部门，21：驾管部门]
     */
    @TableField("org_type")
    private Integer orgType;
    /**
     * 部门类型[1：普通、2：驾管]
     */
    @TableField("dept_type")
    private Integer deptType;
    /**
     * 状态[10：启用，20：禁用]
     */
    private Integer status;
    /**
     * 公司id
     */
    @TableField("company_id")
    private Integer companyId;
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
    /**
     * 机构简称
     */
    @TableField("short_org_name")
    private String shortOrgName;

}
