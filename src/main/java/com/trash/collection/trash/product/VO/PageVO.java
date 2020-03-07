package com.trash.collection.trash.product.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class PageVO {

    /**
     * 第几页
     * */
    @NotNull(message = "请选择第几页")
    private Integer pageIndex;

    /**
     * 每页数量
     * */
    @NotNull(message = "请选择每页数量")
    private Integer pageSize;
}
