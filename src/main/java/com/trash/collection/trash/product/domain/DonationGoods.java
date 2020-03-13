package com.trash.collection.trash.product.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author seth
 * @since 2020-02-26
 */
@Data
@Accessors(chain = true)
@TableName("pr_donation_goods")
public class DonationGoods implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     * 捐赠物品信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 商品种类id
     */
    @TableField("product_kind_id")
    private Long productKindId;
    /**
     * 捐赠物品物流信息id
     * */
    @TableField("donation_logistics_id")
    private Long donationLogisticsId;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 上门取货时间
     */
    @TableField("visit_time")
    private Date visitTime;
    /**
     * 捐赠数量
     */
    @TableField("donation_count")
    private Integer donationCount;
    /**
     * 捐赠物品获得的积分
     */
    @TableField("acquire_score")
    private BigDecimal acquireScore;
    /**
     * 捐赠物品图片
     */
    @TableField("goods_images")
    private String goodsImages;
    /**
     * 备注
     * */
    private String remark;
    /**
     * 捐赠物品物流状态【
     * 10、等待工作人员上门回收；
     * 20、工作人员已上门；
     * 21、已派送工作人员按照约定时间上门；
     * 30、捐赠物品已入库；
     * 40、捐赠物品已出库；
     * 50、捐赠物品已送至需要的人群】
     * */
    @TableField("logistics_status")
    private Integer logisticsStatus;
    /**
     * 捐赠物品状态【1、积分审核中，0、禁用；2、积分已发放给用户】
     * */
    private Integer state;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

}
