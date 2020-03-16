package com.trash.collection.trash.product.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: Seth
 * @Date: 2020/3/16 19:42
 * @Version 1.0
 * 工作人员信息VO
 */
@Data
@Accessors(chain = true)
public class WorkerMessageVO extends PageVO implements Serializable {

    /**
     * 查询参数
     * */
    String param;

    /**
     * 工作人员状态
     * */
    Integer state;

}
