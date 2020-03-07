package com.trash.collection.trash.score.service.impl;

import com.trash.collection.trash.score.domain.ProductOrder;
import com.trash.collection.trash.score.dao.ProductOrderMapper;
import com.trash.collection.trash.score.service.ProductOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-03-07
 */
@Service
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder> implements ProductOrderService {

}
