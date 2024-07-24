package com.sdkmerge.combine.callback;

//渠道初始化
public interface IInitSDKCallback {
    void onSuccess();
    void onExit();
    void onNetworkError();
    void onFailure(String msg);
}
