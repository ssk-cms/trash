package com.trash.collection.trash.user.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统用户Token
 *
 * @author seth
 * @since 2019-12-28
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_token")
public class UserToken implements Serializable{

    private static final long serialVersionUID=1L;

    @TableId("user_id")
    private Integer userId;
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    @TableField("expire_time")
    private Date expireTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

}
