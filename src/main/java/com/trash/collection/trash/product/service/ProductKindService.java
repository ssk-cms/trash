package com.trash.collection.trash.product.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.product.domain.ProductKind;
import com.baomidou.mybatisplus.service.IService;

/**
 * 服务类
 *
 * @author seth
 * @since 2020-01-19
 */
public interface ProductKindService extends IService<ProductKind> {

    /**
     * 新增商品种类
     */
    void add(ProductKind productKind);

    /**
     * 归档商品种类
     */
    void forbidden(Long productKindId);

    /**
     * 查看商品种类列表
     */
    Page<ProductKind> list(Page<ProductKind> page, String param, Integer state);

    /**
     * 编辑商品种类
     * */
    void updateMsg(ProductKind productKind);

    /**
     * 判断传入参数是否完整
     * */
    Response judgeParam();

    /**
     * 判断传入参数是否完整
     * @param message
     * */
    Response judge(String message);
}
