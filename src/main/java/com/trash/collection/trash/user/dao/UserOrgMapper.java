package com.trash.collection.trash.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.trash.collection.trash.user.domain.UserOrg;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与组织机构对应关系 Mapper 接口
 *
 * @author seth
 * @since 2019-12-28
 */
@Mapper
public interface UserOrgMapper extends BaseMapper<UserOrg> {

}
