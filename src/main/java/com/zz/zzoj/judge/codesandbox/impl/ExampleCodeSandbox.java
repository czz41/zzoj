package com.zz.zzoj.judge.codesandbox.impl;

import com.zz.zzoj.judge.codesandbox.CodeSandbox;
import com.zz.zzoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zz.zzoj.judge.codesandbox.model.ExecuteCodeResponse;

public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("示例沙箱启动");
        return null;
    }
}
