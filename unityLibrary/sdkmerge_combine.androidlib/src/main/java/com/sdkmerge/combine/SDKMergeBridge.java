package com.sdkmerge.combine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.morechili.yuki.sdkmerge.IDefCallback;
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

import java.util.ArrayList;
import java.util.List;

public class SDKMergeBridge {

    public static String GetChannelName() { return SDKMergeCombine.getInstance().getChannelName(); }
    public static boolean IsEnablePayOrder() { return SDKMergeCombine.getInstance().isEnablePayOrder(); }
    public static boolean IsChannelDebug() { return SDKMergeCombine.getInstance().isChannelDebug(); }
    public static boolean IsSandbox() { return SDKMergeCombine.getInstance().isSandbox(); }
    public static void SetDebug(boolean debug) { SDKMergeCombine.getInstance().setDebug(debug); }
    public static void SetSandbox(boolean sandbox) { SDKMergeCombine.getInstance().setSandbox(sandbox); }
    public static void SetChannelDebug(boolean debug) { SDKMergeCombine.getInstance().setChannelDebug(debug); }

    public static void SetInitSDK(IInitSDKCallback iInitSDKCallback) {
        SDKMergeCombine.getInstance().setInitSDK(iInitSDKCallback);
    }
    public static void InitSDK() {
        SDKMergeCombine.getInstance().initSDK();
    }

    public static void SetNotificationCenterCallback(ISetNotificationCallback iSetNotificationCallback) {
        SDKMergeCombine.getInstance().setNotificationCenterCallback(iSetNotificationCallback);
    }

    public static void SetNotificationCenter(boolean isEnb) {
        SDKMergeCombine.getInstance().setNotificationCenter(isEnb);
    }

    public static void SetReportErrorInfoCallback(IReportErrorInfoCallback iReportErrorInfoCallback){
        SDKMergeCombine.getInstance().setReportErrorInfoCallback(iReportErrorInfoCallback);
    }

    public static void SetAccountChangedCallback(IAccountChangedCallback iAccountChangedCallback) {
        SDKMergeCombine.getInstance().setAccountChangedCallback(iAccountChangedCallback);
    }
    public static void SetUserId(String userId) {
        SDKMergeCombine.getInstance().setUserId(userId);
    }

    public static void SetSignIn(ISignInCallback iSignInCallback) {
        SDKMergeCombine.getInstance().setSignIn(iSignInCallback);
    }
    public static void SignIn() {
        SDKMergeCombine.getInstance().signIn();
    }

    public static void SetSilentSignIn(ISilentSignInCallback iSilentSignInCallback){
        SDKMergeCombine.getInstance().setSilentSignIn(iSilentSignInCallback);
    }
    public static void SilentSignIn(){
        SDKMergeCombine.getInstance().silentSignIn();
    }

    public static void SetSignOut(ISignOutCallback iSignOutCallback){
        SDKMergeCombine.getInstance().setSignOut(iSignOutCallback);
    }
    public static void SignOut(){
        SDKMergeCombine.getInstance().signOut();
    }

    public static void SetCancelAuthorization(ICancelAuthorizationCallback iCancelAuthorizationCallback){
        SDKMergeCombine.getInstance().setCancelAuthorization(iCancelAuthorizationCallback);
    }
    public static void CancelAuthorization(){
        SDKMergeCombine.getInstance().cancelAuthorization();
    }

    public static boolean IsSupportAntiAddiction() { return SDKMergeCombine.getInstance().isSupportAntiAddiction(); }
    public static String QueryAntiAddictionInfo() { return SDKMergeCombine.getInstance().queryAntiAddictionInfo(); }

    public static void SetAntiAddictionDurationLimit(IDurationLimitCallback iDurationLimitCallback) {
        SDKMergeCombine.getInstance().setAntiAddictionDurationLimit(iDurationLimitCallback);
    }

    public static void AddPayAmountMonth(long price) { SDKMergeCombine.getInstance().addPayAmountMonth(price); }
    public static void RedPayAmountMonth(long price) { SDKMergeCombine.getInstance().redPayAmountMonth(price); }
    public static long QueryPayAmountMonth() { return SDKMergeCombine.getInstance().queryPayAmountMonth(); }

    public static void SetExitGame(IExitCallback iExitCallback){
        SDKMergeCombine.getInstance().setExitGame(iExitCallback);
    }
    public static void ExitGame(){
        SDKMergeCombine.getInstance().exitGame();
    }

    public static void SetPayType(int type){
        SDKMergeCombine.getInstance().setPayType(type);
    }
    public static void SetPay(IPayCallback iPayCallback){
        SDKMergeCombine.getInstance().setPay(iPayCallback);
    }
    public static void Pay(String id,float price,String itemName,String desc,String devPayload){
        SDKMergeCombine.getInstance().pay(id,price,itemName,desc, devPayload);
    }

    public static boolean IsQueryProductInfoFromSdkServer(){ return SDKMergeCombine.getInstance().isQueryProductInfoFromSdkServer(); }
    public static void SetQueryProductInfo(IQueryProductInfoCallback iQueryProductInfoCallback){
        SDKMergeCombine.getInstance().setQueryProductInfo(iQueryProductInfoCallback);
    }
    public static void QueryProductInfo(String productIdListJson){
        SDKMergeCombine.getInstance().queryProductInfo(new Gson().fromJson(productIdListJson, new TypeToken<List<String>>(){}.getType()));
    }

    public static void SetQueryOwnedPurchase(IQueryOwnedPurchasesCallback iQueryOwnedPurchasesCallback){
        SDKMergeCombine.getInstance().setQueryOwnedPurchases(iQueryOwnedPurchasesCallback);
    }
    public static void QueryOwnedPurchase(){
        SDKMergeCombine.getInstance().queryOwnedPurchases();
    }

    public static void ConsumeOwnedPurchase(String cpOrderId,String iapData){
        SDKMergeCombine.getInstance().consumeOwnedPurchase(cpOrderId, iapData);
    }

    public static boolean IsBlack() {
        return SDKMergeCombine.getInstance().isBlack();
    }
    public static boolean IsAbroad() {
        return SDKMergeCombine.getInstance().isAbroad();
    }
    public static boolean IsLoadedVideoAd(){
        return SDKMergeCombine.getInstance().isLoadedVideoAd();
    }
    public static boolean GetPersonalRecommend() {
        return SDKMergeCombine.getInstance().getPersonalRecommend();
    }

    public static void SetPersonalRecommendCallback(IPersonalRecommendCallback iPersonalRecommendCallback) {
        SDKMergeCombine.getInstance().setPersonalRecommendCallback(iPersonalRecommendCallback);
    }
    public static void SetPersonalRecommend(boolean flag) {
        SDKMergeCombine.getInstance().setPersonalRecommend(flag);
    }

    public static void SetRewardAdCallback(IRewardAdCallback callback) {
        SDKMergeCombine.getInstance().setRewardAdCallback(callback);
    }
    public static void ShowRewardAd() {
        SDKMergeCombine.getInstance().showRewardAd();
    }

    public static void SetNativeAdCallback(INativeAdCallback callback) {
        SDKMergeCombine.getInstance().setNativeAdCallback(callback);
    }
    public static void ShowNativeAd() {
        SDKMergeCombine.getInstance().showNativeAd();
    }

    public static void SetInterstitialAdCallback(IInterstitialAdCallback callback) {
        SDKMergeCombine.getInstance().setInterstitialAdCallback(callback);
    }
    public static void ShowInterstitialAd() {
        SDKMergeCombine.getInstance().showInterstitialAd();
    }

    public static boolean HasMoreGame() {return SDKMergeCombine.getInstance().hasMoreGame();}
    public static void ShowMoreGame() {SDKMergeCombine.getInstance().showMoreGame();}
    public static boolean HasJumpToUpdateGame() {return SDKMergeCombine.getInstance().hasJumpToUpdateGame();}
    public static void JumpToUpdateGame() {SDKMergeCombine.getInstance().jumpToUpdateGame();}
    public static boolean HasExit(){
        return SDKMergeCombine.getInstance().hasExit();
    }
    public static void DisableExit(boolean bo) {
        SDKMergeCombine.getInstance().disableExit(bo);
    }
    public static void ShowToast(String msg) {
        SDKMergeCombine.getInstance().showToast(msg);
    }
}
