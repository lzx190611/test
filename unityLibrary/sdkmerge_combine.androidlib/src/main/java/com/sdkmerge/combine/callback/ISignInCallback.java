package com.sdkmerge.combine.callback;

public interface ISignInCallback {
    void onSuccess(String authAccountJson);
    void onFailure(String code);
    void onCancel();
}
