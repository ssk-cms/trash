package com.trash.collection.trash.commercial.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.commercial.domain.CommercialTenant;
import com.baomidou.mybatisplus.service.IService;
import com.trash.collection.trash.product.VO.PageVO;

import java.util.List;

/**
 * 商户加盟表 服务类
 *
 * @author Seth
 * @since 2020-05-04
 */
public interface CommercialTenantService extends IService<CommercialTenant> {

    /**
     * 门户-商户填写商户信息
     * */
    void add(CommercialTenant commercialTenant);

    /**
     * 门户--根据信誉积分显示信誉积分最高的4条商户信息
     * */
    List<CommercialTenant> getListByScore();

    /**
     * 管理员--查看所有商户信息
     * */
    Page getList(PageVO pageVO, String name);

    /**
     * 管理员---审核给与商户信誉分
     * */
    void setScore(CommercialTenant commercialTenant);
}
