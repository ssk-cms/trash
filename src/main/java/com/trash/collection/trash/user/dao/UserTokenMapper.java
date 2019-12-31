package com.trash.collection.trash.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.trash.collection.trash.user.domain.UserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token Mapper 接口
 *
 * @author seth
 * @since 2019-12-28
 */
@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {

}
