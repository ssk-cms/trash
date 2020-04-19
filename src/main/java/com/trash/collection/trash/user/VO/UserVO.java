package com.trash.collection.trash.user.VO;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Seth
 * @Date: 2020/4/15 19:49
 * @Version 1.0
 * 用户信息VO
 */
@Data
@Accessors(chain = true)
public class UserVO {

    /**
     * 用户id
     * */
    private Integer id;

    /**
     * 用户姓名
     * */
    private String username;

    /**
     * 是否是管理员[0、不是；1、是]
     * */
    private Integer isSuperuser;
}
