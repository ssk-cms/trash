package com.trash.collection.trash.product.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 前端商品种类返回类
 * */
@Data
@Accessors(chain = true)
public class ProductKindVO {

    /**
     * 商品种类id
     */
    private Long id;
    /**
     * 商品种类名称
     */
    private String productName;
}
