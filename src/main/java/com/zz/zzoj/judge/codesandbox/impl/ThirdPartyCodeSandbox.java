package com.zz.zzoj.judge.codesandbox.impl;

import com.zz.zzoj.judge.codesandbox.CodeSandbox;
import com.zz.zzoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zz.zzoj.judge.codesandbox.model.ExecuteCodeResponse;

public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方沙箱启动");
        return null;
    }
}
