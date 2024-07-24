package com.sdkmerge.combine.callback;

public interface ICancelAuthorizationCallback {
    void onSuccess();
    void onFailure(String err);
}
