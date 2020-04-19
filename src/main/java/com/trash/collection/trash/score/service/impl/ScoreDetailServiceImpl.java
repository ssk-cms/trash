package com.trash.collection.trash.score.service.impl;

import com.trash.collection.trash.score.VO.ScoreDetailVO;
import com.trash.collection.trash.score.domain.ScoreDetail;
import com.trash.collection.trash.score.dao.ScoreDetailMapper;
import com.trash.collection.trash.score.service.ScoreDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-03-07
 */
@Service
public class ScoreDetailServiceImpl extends ServiceImpl<ScoreDetailMapper, ScoreDetail> implements ScoreDetailService {

    /**
     * 获取积分详情
     * */
    @Override
    public List<ScoreDetailVO> selectScoreDetail(Long userId){
        List<ScoreDetailVO> scoreDetailVOList = this.baseMapper.selectScoreDeatil(userId);
        return scoreDetailVOList;
    }
}
