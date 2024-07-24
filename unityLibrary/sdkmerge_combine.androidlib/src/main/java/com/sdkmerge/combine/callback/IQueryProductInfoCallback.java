package com.sdkmerge.combine.callback;

public interface IQueryProductInfoCallback {
    void onSuccess(String productListJson);
    void onFailure(String returnCode);
    void onAreaNotSupported();  //不支持区域
}
