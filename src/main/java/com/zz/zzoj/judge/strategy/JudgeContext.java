package com.zz.zzoj.judge.strategy;

import com.zz.zzoj.model.dto.question.JudgeCase;
import com.zz.zzoj.judge.codesandbox.model.JudgeInfo;
import com.zz.zzoj.model.entity.Question;
import com.zz.zzoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;
    private List<String> inputList;
    private List<String> outputList;
    private List<JudgeCase>judgeCaseList;
    private Question question;
    private QuestionSubmit questionSubmit;
}
