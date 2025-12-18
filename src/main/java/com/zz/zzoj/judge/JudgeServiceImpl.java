package com.zz.zzoj.judge;

import cn.hutool.json.JSONUtil;
import com.zz.zzoj.common.ErrorCode;
import com.zz.zzoj.exception.BusinessException;
import com.zz.zzoj.judge.codesandbox.CodeSandbox;
import com.zz.zzoj.judge.codesandbox.CodeSandboxFactory;
import com.zz.zzoj.judge.codesandbox.CodeSandboxProxy;
import com.zz.zzoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zz.zzoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zz.zzoj.model.dto.question.JudgeCase;
import com.zz.zzoj.model.dto.question.JudgeConfig;
import com.zz.zzoj.model.dto.questionsubmit.JudgeInfo;
import com.zz.zzoj.model.entity.Question;
import com.zz.zzoj.model.entity.QuestionSubmit;
import com.zz.zzoj.model.enums.JudgeInfoMessageEnum;
import com.zz.zzoj.model.enums.QuestionSubmitStatusEnum;
import com.zz.zzoj.model.vo.QuestionSubmitVO;
import com.zz.zzoj.service.QuestionService;
import com.zz.zzoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService{
    @Value("${codesandbox.type:example}")
    private String type;
    @Resource
    private QuestionSubmitService questionSubmitService;
    @Resource
    private QuestionService questionService;
    @Override
    public QuestionSubmitVO doJudge(long questionSubmitId) {
        //传入提交的id，获取对应的题目信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if(questionSubmit==null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        //如果不为等待状态，抛出异常
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WATING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        //更改题目状态为"判题中"，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        //调用代码沙箱，获取执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox=new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        //获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        //根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeInfoMessageEnum judgeInfoMessageEnum=JudgeInfoMessageEnum.WAITING;
        //先判断沙箱执行的结果输出数量是否和预期输出数量相等
        List<String> outputList = executeCodeResponse.getOutputList();
        if(outputList.size()!= inputList.size()){
            judgeInfoMessageEnum=JudgeInfoMessageEnum.WRONG_ANSWER;
            return null;
        }
        //判断每一项输出和预期输出是否相等
        for(int i=0;i<judgeCaseList.size();i++){
            JudgeCase judgeCase = judgeCaseList.get(i);
            if(!judgeCase.getOutput().equals(outputList.get(i))){
                judgeInfoMessageEnum=JudgeInfoMessageEnum.WRONG_ANSWER;
                return null;
            }
        }
        //判断题目限制
        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long needMemoryLimit = judgeConfig.getMemoryLimit();
        Long needTimeLimit = judgeConfig.getTimeLimit();
        if(memory>needMemoryLimit){
            judgeInfoMessageEnum=JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            return null;
        }
        if(time>needTimeLimit){
            judgeInfoMessageEnum=JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            return null;
        }
        return null;

    }
}
