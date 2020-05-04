package com.trash.collection.trash.commercial.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.commercial.domain.CommercialTenant;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户加盟表 Mapper 接口
 *
 * @author Seth
 * @since 2020-05-04
 */
@Mapper
public interface CommercialTenantMapper extends BaseMapper<CommercialTenant> {

    /**
     * 管理员--查看所有商户信息
     * */
    List<CommercialTenant> getList(Page page, @Param("name") String name);
}
