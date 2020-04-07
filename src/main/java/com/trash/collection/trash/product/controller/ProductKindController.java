package com.trash.collection.trash.product.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.common.StatusCode;
import com.trash.collection.trash.product.VO.ProductKindVO;
import com.trash.collection.trash.product.domain.ProductKind;
import com.trash.collection.trash.product.service.ProductKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 控制器
 *
 * @author seth
 * @since 2020-01-19
 */
@RestController
@CrossOrigin
@RequestMapping("/product/productKind")
public class ProductKindController {

    @Autowired
    ProductKindService productKindService;

    /**
     * 添加物品种类
     */
    @PostMapping("/add")
    public Response add(@RequestBody @Validated ProductKind productKind) {
        Response response = new Response();
        productKindService.add(productKind);
        return response;
    }

    /**
     * 归档商品种类
     */
    @GetMapping("/delete")
    public Response delete(Long productKindId) {
        Response response = new Response();
        productKindService.forbidden(productKindId);
        return response;
    }

    /**
     * 查看商品种类列表
     *
     * @param pageIndex 第几页
     * @param pageSize  每页几个内容
     * @param param     查询参数
     * @param state     正常/归档
     */
    @GetMapping("/list")
    public Response list(Integer pageIndex, Integer pageSize, String param, Integer state) {
        Response response = new Response();
        Page<ProductKind> page = new Page<>(pageIndex, pageSize);
        response.setData(productKindService.list(page, param, state));
        return response;
    }

    /**
     * 查询全部全部在用的商品种类列表
     */
    @GetMapping("/selectAll")
    public Response selectAll(int productKindType) {
        if (Objects.isNull(productKindType)){
            return this.productKindService.judge("请选择商品种类类型");
        }
        Response response = new Response();
        List<ProductKind> productKindList = this.productKindService.selectList(new EntityWrapper<ProductKind>()
                .eq("state", ProductKind.NORMAL_STATE)
                .eq("product_kind_type",productKindType));
        List<ProductKindVO> productKindVOList = new ArrayList<>();
        for (ProductKind productKind : productKindList) {
            ProductKindVO productKindVO = new ProductKindVO();
            productKindVO.setId(productKind.getId())
                    .setProductName(productKind.getProductName());
            productKindVOList.add(productKindVO);
        }
        response.setData(productKindVOList);
        return response;
    }

    /**
     * 编辑商品种类信息
     */
    @PostMapping("/update")
    public Response update(@RequestBody ProductKind productKind) {
        Response response = new Response();
        if (Objects.isNull(productKind.getId())) {
            return productKindService.judgeParam();
        }
        productKindService.updateMsg(productKind);
        return response;
    }

    /**
     * 查看具体某一条商品种类内容
     */
    @GetMapping("/selectOneProductKind")
    public Response selectOneMsg(Long productKindId) {
        Response response = new Response();
        if (Objects.isNull(productKindId)) {
            return productKindService.judgeParam();
        }
        response.setData(productKindService.selectById(productKindId));
        return response;
    }

}

