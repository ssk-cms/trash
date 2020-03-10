package com.trash.collection.trash.product.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.domain.Product;
import com.trash.collection.trash.product.service.ProductKindService;
import com.trash.collection.trash.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 控制器
 *
 * @author seth
 * @since 2020-01-19
 */
@RestController
@RequestMapping("/product/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductKindService productKindService;

    /**
     * 添加商品
     */
    @PostMapping("/addProduct")
    public Response addProduct(@RequestBody Product product) {
        Response response = new Response();
        productService.addProduct(product);
        return response;
    }

    /**
     * 查看商品列表
     */
    @GetMapping("/productList")
    public Response productList(Integer pageIndex, Integer pageSize, String param, Integer state, Long productKindId) {
        Response response = new Response();
        Page<Product> page = new Page<>(pageIndex, pageSize);
        response.setData(productService.productList(page, param, state, productKindId));
        return response;
    }

    /**
     * 编辑商品
     * */
    @PostMapping("/update")
    public Response updateProduct(@RequestBody Product product){
        Response response = new Response();
        productService.updateProduct(product);
        return response;
    }

    /**
     * 归档商品
     * */
    @GetMapping("/deleteProduct")
    public Response deleteProduct(Long productId){
        Response response = new Response();
        if (Objects.isNull(productId)){
            return productKindService.judgeParam();
        }
        productService.deleteProduct(productId);
        return response;
    }

    /**
     * 查看单一商品内容
     * */
    @GetMapping("/selectOneProduct")
    public Response selectOneProduct(Long productId){
        Response response = new Response();
        if (Objects.isNull(productId)){
            productKindService.judgeParam();
        }
        response.setData(productService.selectById(productId));
        return response;
    }
}

