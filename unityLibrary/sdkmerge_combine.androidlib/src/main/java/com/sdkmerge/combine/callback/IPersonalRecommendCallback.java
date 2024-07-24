package com.sdkmerge.combine.callback;

//设置个性化推荐
public interface IPersonalRecommendCallback {
    void onSuccess();
    void onNextActivation();
    void onFailure(String msg);
}
