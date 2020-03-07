package com.trash.collection.trash.product.VO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DonationGoodsVO extends PageVO{
    /**
     *捐赠物品状态
     */
    private Integer state;

    /**
     * 捐赠物品名称
     * */
    private String goodsName;
}
