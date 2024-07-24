package com.sdkmerge.combine.callback;

public interface ISilentSignInCallback {
    void onSuccess(String authAccountJson);
    void onFailure(String code);
    void onCancel();
}
