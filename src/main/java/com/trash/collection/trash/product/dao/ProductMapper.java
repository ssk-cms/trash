package com.trash.collection.trash.product.dao;

import com.trash.collection.trash.product.domain.Product;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 *
 * @author seth
 * @since 2020-01-19
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
