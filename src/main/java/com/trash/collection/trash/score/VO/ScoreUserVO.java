package com.trash.collection.trash.score.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 返回用户可用积分VO
 * */
@Data
@Accessors(chain = true)
public class ScoreUserVO {
    /**
     * 用户可用积分
     * */
    private BigDecimal residuceScore;
}
