package com.sdkmerge.combine.callback;

public interface IQueryOwnedPurchasesCallback {
    void onSuccess(String payCompleteInfoListJson);
    void onFailure(String returnCode);
}
