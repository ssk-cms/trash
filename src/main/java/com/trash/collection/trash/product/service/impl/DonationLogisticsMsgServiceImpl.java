package com.trash.collection.trash.product.service.impl;

import com.trash.collection.trash.product.domain.DonationLogisticsMsg;
import com.trash.collection.trash.product.dao.DonationLogisticsMsgMapper;
import com.trash.collection.trash.product.service.DonationLogisticsMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *
 * @author seth
 * @since 2020-02-26
 */
@Service
public class DonationLogisticsMsgServiceImpl extends ServiceImpl<DonationLogisticsMsgMapper, DonationLogisticsMsg>implements DonationLogisticsMsgService {

    /**
     * 查询捐赠物品物流信息
     * */
    @Override
    public List<DonationLogisticsMsg> selectByDonationsGoodsId(Long donationsGoodsId){
        List<DonationLogisticsMsg> logisticsMsgs = this.baseMapper.selectByDonationsGoodsId(donationsGoodsId);
        return logisticsMsgs;
    }
}
