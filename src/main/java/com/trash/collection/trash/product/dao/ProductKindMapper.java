package com.trash.collection.trash.product.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.product.domain.ProductKind;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author seth
 * @since 2020-01-19
 */
@Mapper
public interface ProductKindMapper extends BaseMapper<ProductKind> {

    List<ProductKind> selectProductKind(Page<ProductKind> page, @Param("param") String param,@Param("state") Integer state);

    /**
     * c查看商品种类总的记录数
     * */
    int selectTotal(Integer state);
}
