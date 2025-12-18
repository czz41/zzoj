package com.zz.zzoj.judge;

import com.zz.zzoj.model.vo.QuestionSubmitVO;

public interface JudgeService {
    QuestionSubmitVO doJudge(long questionSubmitId);
}
