package com.trash.collection.trash.product.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.domain.Product;
import com.trash.collection.trash.product.dao.ProductMapper;
import com.trash.collection.trash.product.service.ProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *  服务实现类
 *
 * @author seth
 * @since 2020-01-19
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>implements ProductService {
        @Autowired
        ProductService productService;

        @Autowired
        ProductMapper productMapper;

        @Override
        @Transactional
        public void addProduct(Product product){
                Date date = new Date();
                product.setCreateTime(date)
                        .setModifyTime(date)
                        .setState(Product.NORMAL_STATE);
                productService.insert(product);
        }

        /**
         * 查看商品列表
         * */
        @Override
        public Page<Product> productList(Page<Product> page,String param,Integer state,Long productKindId){
                page.setRecords(productMapper.productList(page,param,state,productKindId));
                return page;
        }

        /**
         * 编辑商品
         * */
        @Override
        @Transactional
        public void updateProduct(Product product){
                product.setModifyTime(new Date());
                productService.updateById(product);
        }

        /**
         * 归档商品
         * */
        @Override
        @Transactional
        public void deleteProduct(Long productId){
                Product product = new Product().setId(productId)
                        .setModifyTime(new Date())
                        .setState(Product.FORBIDEN_STATE);
                productService.updateById(product);
        }
}
