package com.sdkmerge.bridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.sdkmerge.combine.SDKMergeCombine;
import com.unity3d.player.UnityPlayerActivity;

public class SDKMergeActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SDKMergeCombine.getInstance().onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SDKMergeCombine.getInstance().onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SDKMergeCombine.getInstance().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SDKMergeCombine.getInstance().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SDKMergeCombine.getInstance().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SDKMergeCombine.getInstance().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SDKMergeCombine.getInstance().onRestart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SDKMergeCombine.getInstance().onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SDKMergeCombine.getInstance().onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SDKMergeCombine.getInstance().onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onBackPressed() {
        if (SDKMergeCombine.getInstance().onBackPressed()) super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (SDKMergeCombine.getInstance().onKeyDown(i,keyEvent)) return false;
        return super.onKeyDown(i, keyEvent);
    }
}
