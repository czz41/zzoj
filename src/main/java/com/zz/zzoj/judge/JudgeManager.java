package com.zz.zzoj.judge;

import com.zz.zzoj.judge.strategy.DefaultJudgeStrategy;
import com.zz.zzoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.zz.zzoj.judge.strategy.JudgeContext;
import com.zz.zzoj.judge.strategy.JudgeStrategy;
import com.zz.zzoj.model.dto.questionsubmit.JudgeInfo;
import com.zz.zzoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

@Service
public class JudgeManager {
    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy=new DefaultJudgeStrategy();
        if("java".equals(language)){
            judgeStrategy=new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
