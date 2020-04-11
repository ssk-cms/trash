package com.trash.collection.trash.product.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.VO.ProductVO;
import com.trash.collection.trash.product.domain.Product;
import com.baomidou.mybatisplus.service.IService;

/**
 *  服务类
 *
 * @author seth
 * @since 2020-01-19
 */
public interface ProductService extends IService<Product> {

    void addProduct(Product product);

    /**
     * 查看商品列表
     * */
    Page<ProductVO> productList(Page<ProductVO> page, String param, Integer state, Long productKindId);

    /**
     * 编辑商品
     * */
    void updateProduct(Product product);

    /**
     * 归档商品
     * */
    void deleteProduct(Long productId);
}
