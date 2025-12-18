package com.zz.zzoj.judge.codesandbox;

import com.zz.zzoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zz.zzoj.judge.codesandbox.model.ExecuteCodeResponse;

public interface CodeSandbox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
