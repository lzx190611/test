package com.sdkmerge.combine.callback;

//原生广告
public interface INativeAdCallback {
    void onShow();
    void onFailed(String msg);
    void onClosed();
    void onClicked();
}
