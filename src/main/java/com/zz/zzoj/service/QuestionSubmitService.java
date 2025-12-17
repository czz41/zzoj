package com.zz.zzoj.service;

import com.zz.zzoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zz.zzoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zz.zzoj.model.entity.User;

/**
* @author 陈长江
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2025-12-17 23:01:00
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest  题目提交信息
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
