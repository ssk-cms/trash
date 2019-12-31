package com.trash.collection.trash.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.trash.collection.trash.user.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理 Mapper 接口
 *
 * @author seth
 * @since 2019-12-28
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
