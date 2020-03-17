package com.trash.collection.trash.score.VO;

import com.trash.collection.trash.product.VO.PageVO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Seth
 * @Date: 2020/3/17 20:27
 * @Version 1.0
 * 用户查询自己捐赠物品订单列表VO
 */
@Data
@Accessors(chain = true)
public class UserGoodsOrderVO extends PageVO {

    /**
     * 用户id
     * */
    private Long userId;

    /**
     * 订单状态
     * */
    private Integer state;
}
