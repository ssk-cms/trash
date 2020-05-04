package com.trash.collection.trash.commercial.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.commercial.domain.CommercialTenant;
import com.trash.collection.trash.commercial.dao.CommercialTenantMapper;
import com.trash.collection.trash.commercial.service.CommercialTenantService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.trash.collection.trash.product.VO.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商户加盟表 服务实现类
 *
 * @author Seth
 * @since 2020-05-04
 */
@Service
public class CommercialTenantServiceImpl extends ServiceImpl<CommercialTenantMapper, CommercialTenant> implements CommercialTenantService {

    /**
     * 门户-商户填写商户信息
     */
    @Override
    @Transactional
    public void add(CommercialTenant commercialTenant) {
        Date date = new Date();
        commercialTenant.setModifyTime(date)
                .setCreateTime(date);
        this.baseMapper.insert(commercialTenant);
    }

    /**
     * 门户--根据信誉积分显示信誉积分最高的4条商户信息
     */
    @Override
    public List<CommercialTenant> getListByScore() {
        List<CommercialTenant> commercialTenantList = this.baseMapper.selectList(new EntityWrapper<CommercialTenant>().orderBy("reputation_score", true)
                .last("limit 4"));
        return commercialTenantList;
    }

    /**
     * 管理员--查看所有商户信息
     */
    @Override
    public Page getList(PageVO pageVO, String name) {
        Page page = new Page(pageVO.getPageIndex(), pageVO.getPageSize());
        page.setRecords(this.baseMapper.getList(page, name));
        return page;
    }

    /**
     * 管理员---审核给与商户信誉分
     * */
    @Override
    @Transactional
    public void setScore(CommercialTenant commercialTenant){
        CommercialTenant commercialTenantResult = new CommercialTenant();
        commercialTenantResult.setId(commercialTenant.getId())
                .setReputationScore(commercialTenant.getReputationScore())
                .setModifyTime(new Date());
        this.baseMapper.updateById(commercialTenantResult);
    }
}
