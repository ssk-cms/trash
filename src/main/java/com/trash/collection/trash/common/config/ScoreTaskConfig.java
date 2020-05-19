package com.trash.collection.trash.common.config;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.trash.collection.trash.product.domain.DonationGoods;
import com.trash.collection.trash.product.service.DonationGoodsService;
import com.trash.collection.trash.score.domain.ScoreUser;
import com.trash.collection.trash.score.service.ScoreUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: Seth
 * @Date: 2020/5/19 19:53
 * @Version 1.0
 */
@Configuration
@EnableScheduling
@Component
public class ScoreTaskConfig {

    @Autowired
    DonationGoodsService donationGoodsService;

    @Autowired
    ScoreUserService scoreUserService;

    protected static final Logger logger = LoggerFactory.getLogger(ScoreTaskConfig.class);

    /**
     * 每天早上4点执行一次核对活跃用户的定时任务，给活跃用户增加15积分
     */
    @Scheduled(cron = "0 0 4 * * ?")
    private void setScoreTask() {
        try {
            System.out.println("================核对活跃用户，定时任务开启===============");
            List<Integer> userIdList = this.donationGoodsService.getUserByScore();
            DonationGoods donationGoods = new DonationGoods().setModifyTime(new Date())
                    .setScoreState(1);
            for (Integer userId : userIdList) {
                ScoreUser scoreUser = scoreUserService.selectOne(new EntityWrapper<ScoreUser>().eq("user_id", userId));
                BigDecimal totalscore = scoreUser.getTotalScore().add(new BigDecimal(15));
                BigDecimal totalAcquireScore = scoreUser.getResiduceScore().add(new BigDecimal(15));
                ScoreUser score = new ScoreUser();
                score.setTotalScore(totalscore)
                        .setResiduceScore(totalAcquireScore)
                        .setModifyTime(new Date());
                //更新用户总积分和剩余积分
                this.scoreUserService.update(score,new EntityWrapper<ScoreUser>().eq("user_id",userId));
                //更改用户的捐赠物品积分已发放
                this.donationGoodsService.update(donationGoods,new EntityWrapper<DonationGoods>().eq("user_id",userId).eq("score_state",0));
            }
        } catch (Exception e) {
            logger.info("定时任务异常，异常信息={}", e.getMessage());
        }
    }
}
