package com.trash.collection.trash.product.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.VO.ProductVO;
import com.trash.collection.trash.product.domain.Product;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author seth
 * @since 2020-01-19
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<ProductVO> productList(Page<ProductVO> page, @Param("param") String param, @Param("state") Integer state, @Param("productKindId") Long productKindId);
}
