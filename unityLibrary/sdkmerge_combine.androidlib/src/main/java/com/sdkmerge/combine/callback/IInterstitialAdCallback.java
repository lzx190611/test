package com.sdkmerge.combine.callback;

//插屏广告
public interface IInterstitialAdCallback {
    void onShow();
    void onFailed(String msg);
    void onClosed();
    void onClicked();
}
