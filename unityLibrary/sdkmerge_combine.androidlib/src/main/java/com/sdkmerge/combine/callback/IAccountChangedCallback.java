package com.sdkmerge.combine.callback;

//账号切换
public interface IAccountChangedCallback {
    void onAccountChanged(String authAccountJson);
    void onAccountLogout();
}
