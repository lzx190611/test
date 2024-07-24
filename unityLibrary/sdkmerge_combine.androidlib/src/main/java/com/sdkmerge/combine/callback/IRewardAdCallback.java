package com.sdkmerge.combine.callback;

//奖励视频广告
public interface IRewardAdCallback {
    void onShow();
    void onFailed(String msg);
    void onClosed();
    void onRewarded();
}
