package com.zz.zzoj.judge.strategy;

import com.zz.zzoj.model.dto.questionsubmit.JudgeInfo;

public interface JudgeStrategy {
    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
