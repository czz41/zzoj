package com.zz.zzoj.judge;

import com.zz.zzoj.model.entity.QuestionSubmit;

public interface JudgeService {
    QuestionSubmit doJudge(long questionSubmitId);
}
