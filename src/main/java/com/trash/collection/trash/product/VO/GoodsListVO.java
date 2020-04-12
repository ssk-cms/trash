package com.trash.collection.trash.product.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class GoodsListVO {
    /**
     * 捐赠物品信息id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商品种类id
     */
    private Long productKindId;
    /**
     * 捐赠物品物流信息id
     * */
    private Long donationLogisticsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 上门取货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date visitTime;
    /**
     * 捐赠数量
     */
    private Integer donationCount;
    /**
     * 捐赠物品获得的积分
     */
    private BigDecimal acquireScore;
    /**
     * 捐赠物品图片
     */
    private String goodsImages;
    /**
     * 备注
     * */
    private String remark;
    /**
     * 捐赠物品物流状态【
     * 0、商品已添加
     * 10、等待工作人员上门回收；
     * 20、工作人员已上门；
     * 21、已派送工作人员按照约定时间上门；
     * 30、捐赠物品已入库；
     * 40、捐赠物品已出库；
     * 50、捐赠物品已送至需要的人群】
     * */
    private Integer logisticsStatus;
    /**
     * 捐赠物品状态【1、积分审核中，0、禁用；2、积分已发放给用户;3、捐赠物品信息已添加，待下单】
     * */
    private Integer state;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date modifyTime;

    /**
     * 商品种类名称
     * */
    private String productKindName;

    /**
     * 用户姓名
     * */
    private String username;
}
