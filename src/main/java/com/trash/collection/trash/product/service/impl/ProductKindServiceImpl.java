package com.trash.collection.trash.product.service.impl;

import com.alipay.api.domain.DataEntry;
import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.common.StatusCode;
import com.trash.collection.trash.product.domain.Product;
import com.trash.collection.trash.product.domain.ProductKind;
import com.trash.collection.trash.product.dao.ProductKindMapper;
import com.trash.collection.trash.product.service.ProductKindService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-01-19
 */
@Service
public class ProductKindServiceImpl extends ServiceImpl<ProductKindMapper, ProductKind> implements ProductKindService {

    @Autowired
    ProductKindMapper productKindMapper;

    /**
     * 新增商品实现方法
     */
    @Override
    @Transactional
    public void add(ProductKind productKind) {
        Date date = new Date();
        productKind.setCreateTime(date)
                .setModifyTime(date)
                .setState(ProductKind.NORMAL_STATE);
        //todo 插入时间和id值有误，正在排查原因
        productKindMapper.insert(productKind);
    }

    /**
     * 归档商品种类
     */
    @Override
    @Transactional
    public void forbidden(Long productKindId) {
        ProductKind productKind = new ProductKind().setId(productKindId)
                .setState(ProductKind.FORBIDDEN_STATE)
                .setModifyTime(new Date());
        productKindMapper.updateById(productKind);
    }

    /**
     * 查看商品种类列表
     */
    @Override
    public Page<ProductKind> list(Page<ProductKind> page, String param, Integer state) {
        //todo 返回时间格式不正确，正在排查原因
        page.setRecords(this.productKindMapper.selectProductKind(page, param, state));
        return page;
    }

    /**
     * 编辑商品种类内容
     * */
    @Override
    @Transactional
    public void updateMsg(ProductKind productKind){
        productKind.setModifyTime(new Date());
        System.err.println(new Date());
        //todo 插入时间不对，正在排查原因
        productKindMapper.updateById(productKind);
    }

    @Override
    public Response judgeParam(){
        Response response = new Response();
        response.setCode(StatusCode.Data_Not_Complete)
                .setStatusCode(StatusCode.Data_Not_Complete)
                .setMessage("请将完整信息进行提交");
        return response;
    }
}
