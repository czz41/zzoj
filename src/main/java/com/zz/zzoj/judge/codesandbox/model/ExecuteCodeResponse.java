package com.zz.zzoj.judge.codesandbox.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.zz.zzoj.model.dto.questionsubmit.JudgeInfo;

import java.util.Date;
import java.util.List;

public class ExecuteCodeResponse {
    /**
     * 运行结果
     */
    private List<String> outputList;
    /**
     * 状态 0 - 待处理、1 - 执行中、2 - 成功、3 - 失败
     */
    private Integer status;
    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
    /**
     * 沙箱错误信息
     */
    private String message;
}
