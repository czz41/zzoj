package com.zz.zzoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.zzoj.annotation.AuthCheck;
import com.zz.zzoj.common.BaseResponse;
import com.zz.zzoj.common.ErrorCode;
import com.zz.zzoj.common.ResultUtils;
import com.zz.zzoj.constant.UserConstant;
import com.zz.zzoj.exception.BusinessException;
import com.zz.zzoj.model.dto.question.QuestionQueryRequest;
import com.zz.zzoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zz.zzoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zz.zzoj.model.entity.Question;
import com.zz.zzoj.model.entity.QuestionSubmit;
import com.zz.zzoj.model.entity.User;
import com.zz.zzoj.model.vo.QuestionSubmitVO;
import com.zz.zzoj.service.QuestionSubmitService;
import com.zz.zzoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author <a href="https://github.com/lizz">程序员鱼皮</a>
 * @from <a href="https://zz.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question/question_submit")
@Slf4j
@Deprecated
public class QuestionSubmitController {

}
