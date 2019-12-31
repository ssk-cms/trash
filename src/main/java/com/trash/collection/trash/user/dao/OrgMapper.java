package com.trash.collection.trash.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.trash.collection.trash.user.domain.Org;
import org.apache.ibatis.annotations.Mapper;

/**
 * 组织架构 Mapper 接口
 *
 * @author seth
 * @since 2019-12-28
 */
@Mapper
public interface OrgMapper extends BaseMapper<Org> {

}
