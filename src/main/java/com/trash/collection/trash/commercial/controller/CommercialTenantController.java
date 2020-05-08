package com.trash.collection.trash.commercial.controller;


import com.trash.collection.trash.commercial.domain.CommercialTenant;
import com.trash.collection.trash.commercial.service.CommercialTenantService;
import com.trash.collection.trash.common.RRException;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.VO.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.ext.ISCII91;

import java.util.Date;
import java.util.Objects;

/**
 * 商户加盟表 控制器
 *
 * @author Seth
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/commercial/commercialTenant")
public class CommercialTenantController {

    @Autowired
    private CommercialTenantService commercialTenantService;

    /**
     * 门户---商户填写商户信息
     */
    @PostMapping("/add")
    public Response add(@RequestBody CommercialTenant commercialTenant) {
        if (Objects.isNull(commercialTenant)) {
            throw new RRException("请填写商户信息");
        }
        if (Objects.isNull(commercialTenant.getName()) || Objects.equals(commercialTenant.getName(),"")){
            throw new RRException("请完整填写商户信息！");
        }
            this.commercialTenantService.add(commercialTenant);
        return new Response();
    }

    /**
     * 门户--根据信誉积分显示信誉积分最高的4条商户信息
     */
    @GetMapping("/getListByScore")
    public Response getListByScore() {
        Response response = new Response();
        response.setData(this.commercialTenantService.getListByScore());
        return response;
    }

    /**
     * 管理员--查看所有商户信息
     */
    @GetMapping("/getList")
    public Response getList(PageVO pageVO, String name) {
        if (Objects.isNull(pageVO)) {
            throw new RRException("请输入页码");
        }
        Response response = new Response();
        response.setData(this.commercialTenantService.getList(pageVO, name));
        return response;
    }

    /**
     * 管理员---审核给与商户信誉分
     */
    @PostMapping("/setScore")
    public Response setScore(@RequestBody CommercialTenant commercialTenant) {
        if (Objects.isNull(commercialTenant)) {
            throw new RRException("请输入信誉积分");
        }
        if (Objects.isNull(commercialTenant.getId()) || Objects.isNull(commercialTenant.getReputationScore())) {
            throw new RRException("请选择商户或重新输入积分");
        }
        this.commercialTenantService.setScore(commercialTenant);
        return new Response();
    }

    /**
     * 管理员--修改商户信息
     * */
    @PostMapping("/update")
    public Response update(@RequestBody CommercialTenant commercialTenant){
        if (Objects.isNull(commercialTenant)){
            throw new RRException("请填写商户信息！");
        }
        if (Objects.isNull(commercialTenant.getId())){
            throw new RRException("请选择商户！");
        }
        commercialTenant.setModifyTime(new Date());
        try {
            this.commercialTenantService.updateById(commercialTenant);
        }catch (Exception e){
            System.out.println("更新商户信息出错！"+e);
        }
        
        return new Response();
    }

    /**
     * 管理员--删除商户信息
     * */
    @PostMapping("/delete")
    public Response delete(Long id){
        if (Objects.isNull(id)){
            throw new RRException("请选择商户！");
        }
        try {
            this.commercialTenantService.deleteById(id);
        }catch (Exception e){
            System.out.println("更新商户信息出错！"+e);
        }
        return new Response();
    }

}

