package com.sdkmerge.combine;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.morechili.yuki.sdkmerge.IAccountChangedListener;
import com.morechili.yuki.sdkmerge.IAdAllCallBackListener;
import com.morechili.yuki.sdkmerge.IDefCallback;
import com.morechili.yuki.sdkmerge.IExitInterface;
import com.morechili.yuki.sdkmerge.IInitListener;
import com.morechili.yuki.sdkmerge.IPayListener;
import com.morechili.yuki.sdkmerge.IPersonalRecommendListener;
import com.morechili.yuki.sdkmerge.IQueryOwnedPurchasesListener;
import com.morechili.yuki.sdkmerge.IQueryProductListener;
import com.morechili.yuki.sdkmerge.ISignInListener;
import com.morechili.yuki.sdkmerge.SDKMergeManager;
import com.morechili.yuki.util.AuthAccountInfo;
import com.morechili.yuki.util.PayCompleteInfo;
import com.morechili.yuki.util.PayLimitInfo;
import com.morechili.yuki.util.ProductInfo;
import com.sdkmerge.combine.callback.IAccountChangedCallback;
import com.sdkmerge.combine.callback.IDurationLimitCallback;
import com.sdkmerge.combine.callback.ICancelAuthorizationCallback;
import com.sdkmerge.combine.callback.IExitCallback;
import com.sdkmerge.combine.callback.IInitSDKCallback;
import com.sdkmerge.combine.callback.IInterstitialAdCallback;
import com.sdkmerge.combine.callback.INativeAdCallback;
import com.sdkmerge.combine.callback.IPayCallback;
import com.sdkmerge.combine.callback.IPersonalRecommendCallback;
import com.sdkmerge.combine.callback.IQueryOwnedPurchasesCallback;
import com.sdkmerge.combine.callback.IQueryProductInfoCallback;
import com.sdkmerge.combine.callback.IReportErrorInfoCallback;
import com.sdkmerge.combine.callback.IRewardAdCallback;
import com.sdkmerge.combine.callback.ISetNotificationCallback;
import com.sdkmerge.combine.callback.ISignInCallback;
import com.sdkmerge.combine.callback.ISignOutCallback;
import com.sdkmerge.combine.callback.ISilentSignInCallback;
import com.unity3d.player.UnityPlayer;

import java.util.List;

public class SDKMergeCombine {

    private static SDKMergeCombine instance;
    private final String TAG = "SDKMergeCombine";
    private boolean mDisableExit = false;

    public static SDKMergeCombine getInstance() {
        if (instance == null) {
            instance = new SDKMergeCombine();
        }

        return instance;
    }

    public void init(Application application, Class<?> buildConfigClass) {
        SDKMergeManager.ins().init(application, buildConfigClass);
    }

    public String getChannelName() {
        return SDKMergeManager.ins().getChannelName();
    }
    public void setDebug(boolean debug){
        SDKMergeManager.ins().setDebug(debug);
    }
    public void setChannelDebug(boolean debug) {
        SDKMergeManager.ins().setChannelDebug(debug);
    }
    public void setSandbox(boolean sandbox) {
        SDKMergeManager.ins().setSandbox(sandbox);
    }
    public boolean isChannelDebug() {
        return SDKMergeManager.ins().isChannelDebug();
    }
    public boolean isSandbox() {
        return SDKMergeManager.ins().isSandbox();
    }
    public boolean isEnablePayOrder(){
        return SDKMergeManager.ins().isEnablePayOrder();
    }

    private IInitSDKCallback iInitSDKCallback;
    public void setInitSDK(IInitSDKCallback iInitSDKCallback){
        this.iInitSDKCallback = iInitSDKCallback;
    }
    public void initSDK(){
        SDKMergeManager.ins().initSdk(new IInitListener() {
            @Override
            public void onSuccess() {
                if (iInitSDKCallback != null) iInitSDKCallback.onSuccess();
            }

            @Override
            public void onExit() {
                if (iInitSDKCallback != null) iInitSDKCallback.onExit();
            }

            @Override
            public void onNetworkError() {
                if (iInitSDKCallback != null) iInitSDKCallback.onNetworkError();
            }

            @Override
            public void onFailure(int i) {
                if (iInitSDKCallback != null) iInitSDKCallback.onFailure(String.valueOf(i));
            }
        });
    }

    public void setReportErrorInfoCallback(IReportErrorInfoCallback iReportErrorInfoCallback) {
        SDKMergeManager.ins().setReportErrorInfo((s, s1) -> {
            if(iReportErrorInfoCallback != null) iReportErrorInfoCallback.onError(s,s1);
        });
    }

    private ISetNotificationCallback iSetNotificationCallback;
    public void setNotificationCenterCallback(ISetNotificationCallback iDefCallback) {
        iSetNotificationCallback = iDefCallback;
    }

    public void setNotificationCenter(boolean isEnb) {
        SDKMergeManager.ins().setNotificationCenter(isEnb, new IDefCallback() {
            @Override
            public void onSuccess() {
                if(iSetNotificationCallback != null) iSetNotificationCallback.onSuccess();
            }

            @Override
            public void onFailure(String s) {
                if(iSetNotificationCallback != null) iSetNotificationCallback.onFailure(s);
            }
        });
    }

    public void setAccountChangedCallback(IAccountChangedCallback iAccountChangedCallback) {
        SDKMergeManager.ins().registerAccountChangedListener(new IAccountChangedListener() {
            @Override
            public void onAccountChanged(AuthAccountInfo authAccountInfo) {
                if(iAccountChangedCallback != null) iAccountChangedCallback.onAccountChanged(new Gson().toJson(authAccountInfo));
            }

            @Override
            public void onAccountLogout() {
                if(iAccountChangedCallback != null) iAccountChangedCallback.onAccountLogout();
            }
        });
    }
    public void setUserId(String userId) {
        SDKMergeManager.ins().setUserId(userId);
    }

    private ISignInCallback iSignInCallback;
    public void setSignIn(ISignInCallback iSignInCallback){
        this.iSignInCallback = iSignInCallback;
    }
    public void signIn(){
        SDKMergeManager.ins().signIn(new ISignInListener() {
            @Override
            public void onSuccess(AuthAccountInfo authAccountInfo) {
                Log.d(TAG, "signIn onSuccess: " + authAccountInfo.toString());
                setUserId(authAccountInfo.openId);
                if (iSignInCallback != null) iSignInCallback.onSuccess(new Gson().toJson(authAccountInfo));
            }

            @Override
            public void onFailure(String s) {
                if (iSignInCallback != null) iSignInCallback.onFailure(s == null ? "-1" : s);
            }

            @Override
            public void onCancel() {
                if (iSignInCallback != null) iSignInCallback.onCancel();
            }
        });
    }

    private ISilentSignInCallback iSilentSignInCallback;
    public void setSilentSignIn(ISilentSignInCallback iSilentSignInCallback){
        this.iSilentSignInCallback = iSilentSignInCallback;
    }
    public void silentSignIn() {
        SDKMergeManager.ins().silentSignIn(new ISignInListener() {
            @Override
            public void onSuccess(AuthAccountInfo authAccountInfo) {
                if (authAccountInfo != null) setUserId(authAccountInfo.openId);
                if (iSilentSignInCallback != null) iSilentSignInCallback.onSuccess(new Gson().toJson(authAccountInfo));
            }

            @Override
            public void onFailure(String s) {
                if (iSilentSignInCallback != null) iSilentSignInCallback.onFailure(s == null ? "-1" : s);
            }

            @Override
            public void onCancel() {
                if (iSilentSignInCallback != null) iSilentSignInCallback.onCancel();
            }
        });
    }

    private ISignOutCallback iSignOutCallback;
    public void setSignOut(ISignOutCallback iSignOutCallback){
        this.iSignOutCallback = iSignOutCallback;
    }
    public void signOut(){
        SDKMergeManager.ins().signOut(new IDefCallback() {
            @Override
            public void onSuccess() {
                if (iSignOutCallback != null) iSignOutCallback.onSuccess();
            }

            @Override
            public void onFailure(String code) {
                if (iSignOutCallback != null) iSignOutCallback.onFailure(code == null ? "-1" : code);
            }
        });
    }

    private ICancelAuthorizationCallback iCancelAuthorizationCallback;
    public void setCancelAuthorization(ICancelAuthorizationCallback iCancelAuthorizationCallback){
        this.iCancelAuthorizationCallback = iCancelAuthorizationCallback;
    }
    public void cancelAuthorization(){
        SDKMergeManager.ins().cancelAuthorization(new IDefCallback() {
            @Override
            public void onSuccess() {
                if (iCancelAuthorizationCallback != null) iCancelAuthorizationCallback.onSuccess();
            }

            @Override
            public void onFailure(String s) {
                if (iCancelAuthorizationCallback != null) iCancelAuthorizationCallback.onFailure(s == null ? "-1" : s);
            }
        });
    }

    public boolean isSupportAntiAddiction() {
        return SDKMergeManager.ins().isSupportAntiAddiction();
    }
    public String queryAntiAddictionInfo() {
        return new Gson().toJson(SDKMergeManager.ins().queryAntiAddictionInfo());
    }
    public void setAntiAddictionDurationLimit(IDurationLimitCallback iDurationLimitCallback) {
        SDKMergeManager.ins().setAntiAddictionDurationLimit(new com.morechili.yuki.sdkmerge.IDurationLimitCallback() {
            @Override
            public void onDurationLimit() {
                if(iDurationLimitCallback != null) iDurationLimitCallback.onDurationLimit();
            }
        });
    }

    public void addPayAmountMonth(long price) {
        SDKMergeManager.ins().addPayAmountMonth(price);
    }
    public void redPayAmountMonth(long price) {
        SDKMergeManager.ins().redPayAmountMonth(price);
    }
    public long queryPayAmountMonth() {
        return SDKMergeManager.ins().queryPayAmountMonth();
    }

    private IExitCallback iExitCallback;
    public void setExitGame(IExitCallback iExitCallback){
        this.iExitCallback = iExitCallback;
    }
    public void exitGame(){
        SDKMergeManager.ins().exitGame(new IExitInterface() {
            @Override
            public void onExit() {
                if (iExitCallback != null) iExitCallback.onExit();
            }

            @Override
            public void onCancel() {
                if (iExitCallback != null) iExitCallback.onCancel();
            }
        });
    }

    public void setPayType(int type){
        Log.d(TAG, "setPayType: " + type);
        SDKMergeManager.ins().setPayType(type);
    }
    private IPayCallback iPayCallback;
    public void setPay(IPayCallback iPayCallback){
        this.iPayCallback = iPayCallback;
    }
    public void pay(String id,float price,String itemName, String desc, String devPayload){
        Log.d(TAG, "pay: " + id);
        Log.d(TAG, "pay: devPayload= " + devPayload);
        SDKMergeManager.ins().pay(id, price, itemName, new IPayListener() {
            @Override
            public void onCreateOrderId(String orderId) {
                Log.d(TAG, "pay onCreateOrderId: " + orderId);
                if (iPayCallback != null) iPayCallback.onCreateOrderId(orderId);
            }

            @Override
            public void onPayComplete(String i, PayCompleteInfo payCompleteInfo) {
                Log.d(TAG, "pay onPayComplete: " + i);
                Log.e(TAG, "pay onPayComplete data: " + payCompleteInfo.toString() );
                if (iPayCallback != null) iPayCallback.onPayComplete(i,new Gson().toJson(payCompleteInfo));
            }

            @Override
            public void onPaySuccess(String i) {
                Log.d(TAG, "pay onPaySuccess: " + i);
                if (iPayCallback != null) iPayCallback.onPaySuccess(i);
            }

            @Override
            public void onPayFailure(String i, String s) {
                if (iPayCallback != null) iPayCallback.onPayFailure(i,s == null ? "-1" : s);
            }

            @Override
            public void onHaveUnconsumedProducts() {
                if (iPayCallback != null) iPayCallback.onHaveUnconsumedProducts();
            }

            @Override
            public void onPayCancel(String i) {
                if (iPayCallback != null) iPayCallback.onPayCancel(i);
            }

            @Override
            public void onPayLimit(String s, PayLimitInfo info) {
                if (iPayCallback != null) iPayCallback.onPayLimit(s,new Gson().toJson(info));
            }
        },desc, devPayload);
    }

    public boolean isQueryProductInfoFromSdkServer() {
        return SDKMergeManager.ins().isQueryProductInfoFromSdkServer();
    }
    private IQueryProductInfoCallback iQueryProductInfoCallback;
    public void setQueryProductInfo(IQueryProductInfoCallback iQueryProductInfoCallback){
        this.iQueryProductInfoCallback = iQueryProductInfoCallback;
    }
    public void queryProductInfo(List<String> productIdList){
        SDKMergeManager.ins().queryProductInfo(productIdList, new IQueryProductListener() {
            @Override
            public void onSuccess(List<ProductInfo> list) {
                if (iQueryProductInfoCallback != null) iQueryProductInfoCallback.onSuccess(new Gson().toJson(list));
            }

            @Override
            public void onFailure(String s) {
                if (iQueryProductInfoCallback != null) iQueryProductInfoCallback.onFailure(s == null ? "-1" : s);
            }

            @Override
            public void onAreaNotSupported() {
                if (iQueryProductInfoCallback != null) iQueryProductInfoCallback.onAreaNotSupported();
            }
        });
    }

    private IQueryOwnedPurchasesCallback iQueryOwnedPurchasesCallback;
    public void setQueryOwnedPurchases(IQueryOwnedPurchasesCallback iQueryOwnedPurchasesCallback){
        Log.d(TAG, "setQueryOwnedPurchases: ");
        this.iQueryOwnedPurchasesCallback = iQueryOwnedPurchasesCallback;
    }
    public void queryOwnedPurchases(){
        Log.d(TAG, "queryOwnedPurchases: ");
        SDKMergeManager.ins().queryOwnedPurchases(new IQueryOwnedPurchasesListener() {
            @Override
            public void onSuccess(List<PayCompleteInfo> list) {
                Log.d(TAG, "queryOwnedPurchases onSuccess: " + list.size());
                if (iQueryOwnedPurchasesCallback != null) iQueryOwnedPurchasesCallback.onSuccess(new Gson().toJson(list));
            }

            @Override
            public void onFailure(String s) {
                Log.d(TAG, "queryOwnedPurchases onFailure: " + s);
                if (iQueryOwnedPurchasesCallback != null) iQueryOwnedPurchasesCallback.onFailure(s == null ? "-1" : s);
            }
        });
    }

    public void consumeOwnedPurchase(String cpOrderId,String iapData){
        Log.d(TAG, "consumeOwnedPurchase: " + iapData);
        SDKMergeManager.ins().consumeOwnedPurchase(cpOrderId,iapData);
    }

    public boolean isBlack() {
        return SDKMergeManager.ins().getAdControl().isBlack();
    }
    public boolean isAbroad() {
        return SDKMergeManager.ins().getAdControl().isAbroad();
    }
    public boolean isLoadedVideoAd() {
        return SDKMergeManager.ins().getAdControl().isLoadedVideoAd();
    }
    public boolean getPersonalRecommend() {
        return SDKMergeManager.ins().getPersonalRecommend();
    }

    private IPersonalRecommendCallback iPersonalRecommendCallback;
    public void setPersonalRecommendCallback(IPersonalRecommendCallback iPersonalRecommendCallback) {
        this.iPersonalRecommendCallback = iPersonalRecommendCallback;
    }
    public void setPersonalRecommend(boolean flag) {
        SDKMergeManager.ins().setPersonalRecommend(flag, new IPersonalRecommendListener() {
            @Override
            public void onSuccess() {
                if(iPersonalRecommendCallback != null) iPersonalRecommendCallback.onSuccess();
            }

            @Override
            public void onNextActivation() {
                if(iPersonalRecommendCallback != null) iPersonalRecommendCallback.onNextActivation();
            }

            @Override
            public void onFailure(String s) {
                if(iPersonalRecommendCallback != null) iPersonalRecommendCallback.onFailure(s == null ? "-1" : s);
            }
        });
    }

    private IRewardAdCallback iRewardAdCallback;
    public void setRewardAdCallback(IRewardAdCallback callback) {
        iRewardAdCallback = callback;
    }
    public void showRewardAd() {
        SDKMergeManager.ins().showVideoAds(UnityPlayer.currentActivity, new IAdAllCallBackListener() {
            @Override
            public void onAdShow() {
                if (iRewardAdCallback != null) iRewardAdCallback.onShow();
            }

            @Override
            public void onAdFailed(String s) {
                if (iRewardAdCallback != null) iRewardAdCallback.onFailed(s == null ? "-1" : s);
            }

            @Override
            public void onAdReady() {
            }

            @Override
            public void onAdClick() {
            }

            @Override
            public void onVideoPlayComplete() {
                if (iRewardAdCallback != null) iRewardAdCallback.onRewarded();
            }

            @Override
            public void onAdClose() {
                if (iRewardAdCallback != null) iRewardAdCallback.onClosed();
            }
        });
    }

    private long lastClickNativeAdTime;
    private boolean isFastNativeAdClick() {
        long curClickTime = System.currentTimeMillis();
        Log.d(TAG, "isFastNativeAdClick: curClickTime-> " + curClickTime + " lastClickNativeAdTime-> " + lastClickNativeAdTime);
        // 两次点击按钮之间的点击间隔不能少于1000毫秒
        if ((curClickTime - lastClickNativeAdTime) < 1000) {
            Log.d(TAG, "isFastNativeAdClick: [NativeAd]触发时间小于1000跳过");
            return true;
        }
        lastClickNativeAdTime = curClickTime;
        return false;
    }

    private INativeAdCallback iNativeAdCallback;
    public void setNativeAdCallback(INativeAdCallback callback) {
        iNativeAdCallback = callback;
    }
    public void showNativeAd() {
        if (isFastNativeAdClick()) return;
        SDKMergeManager.ins().showInsertAds(UnityPlayer.currentActivity, new IAdAllCallBackListener() {
            @Override
            public void onAdShow() {
                if (iNativeAdCallback != null) iNativeAdCallback.onShow();
            }

            @Override
            public void onAdFailed(String s) {
                if (iNativeAdCallback != null) iNativeAdCallback.onFailed(s == null ? "-1" : s);
            }

            @Override
            public void onAdReady() {

            }

            @Override
            public void onAdClick() {
                if (iNativeAdCallback != null) iNativeAdCallback.onClicked();
            }

            @Override
            public void onVideoPlayComplete() {

            }

            @Override
            public void onAdClose() {
                if (iNativeAdCallback != null) iNativeAdCallback.onClosed();
            }
        });
    }

    private long lastClickInterstitialAdTime;
    private boolean isFastInterstitialAdClick() {
        long curClickTime = System.currentTimeMillis();
        Log.d(TAG, "isFastInterstitialAdClick: curClickTime-> " + curClickTime + " lastClickInterstitialAdTime-> " + lastClickInterstitialAdTime);
        // 两次点击按钮之间的点击间隔不能少于1000毫秒
        if ((curClickTime - lastClickInterstitialAdTime) < 1000) {
            Log.d(TAG, "isFastInterstitialAdClick: [InterstitialAd]触发时间小于1000跳过");
            return true;
        }
        lastClickInterstitialAdTime = curClickTime;
        return false;
    }

    private IInterstitialAdCallback iInterstitialAdCallback;
    public void setInterstitialAdCallback(IInterstitialAdCallback callback) {
        iInterstitialAdCallback = callback;
    }
    public void showInterstitialAd() {
        if (isFastInterstitialAdClick()) return;
        SDKMergeManager.ins().showInsertFullAds(UnityPlayer.currentActivity, new IAdAllCallBackListener() {
            @Override
            public void onAdShow() {
                if (iInterstitialAdCallback != null) iInterstitialAdCallback.onShow();
            }

            @Override
            public void onAdFailed(String s) {
                if (iInterstitialAdCallback != null) iInterstitialAdCallback.onFailed(s == null ? "-1" : s);
            }

            @Override
            public void onAdReady() {
            }

            @Override
            public void onAdClick() {
                if (iInterstitialAdCallback != null) iInterstitialAdCallback.onClicked();
            }

            @Override
            public void onVideoPlayComplete() {
            }

            @Override
            public void onAdClose() {
                if (iInterstitialAdCallback != null) iInterstitialAdCallback.onClosed();
            }
        });
    }

    public boolean hasMoreGame() { return SDKMergeManager.ins().hasMoreGame();}
    public void showMoreGame() {SDKMergeManager.ins().showMoreGame();}

    public boolean hasJumpToUpdateGame() { return SDKMergeManager.ins().hasJumpToUpdateGame();}
    public void jumpToUpdateGame() {SDKMergeManager.ins().jumpToUpdateGame();}

    public boolean hasExit(){
        return SDKMergeManager.ins().hasExit();
    }
    //禁用退出
    public void disableExit(boolean bo) {
        mDisableExit = bo;
    }
    public void showToast(String msg) {
        Toast.makeText(UnityPlayer.currentActivity, msg, Toast.LENGTH_LONG).show();
    }
    public void onCreate() {
        SDKMergeManager.ins().onCreateActivity(UnityPlayer.currentActivity);
    }

    public void onDestroy() {
        SDKMergeManager.ins().onDestroy();
    }

    public void onPause() {
        SDKMergeManager.ins().onPause();
    }

    public void onResume() {
        SDKMergeManager.ins().onResume();
    }

    public void onStart() {
        SDKMergeManager.ins().onStart();
    }

    public void onStop() {
        SDKMergeManager.ins().onStart();
    }

    public void onRestart() {
        SDKMergeManager.ins().onRestart();
    }

    public void onNewIntent(Intent intent) {
        SDKMergeManager.ins().onNewIntent(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        SDKMergeManager.ins().onActivityResult(requestCode, resultCode, data);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        SDKMergeManager.ins().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // 返回 true 执行
    public boolean onBackPressed() {
        return !SDKMergeManager.ins().onBackPressed();
    }

    //如果返回true 中断
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDisableExit) return false;
            SDKMergeManager.ins().exitGame(new IExitInterface() {
                @Override
                public void onExit() {
                    if(iExitCallback != null) iExitCallback.onExit();
                    Process.killProcess(Process.myPid());
                }

                @Override
                public void onCancel() {
                    if(iExitCallback != null) iExitCallback.onCancel();
                }
            });
            return true;
        }
        return false;
    }
}
