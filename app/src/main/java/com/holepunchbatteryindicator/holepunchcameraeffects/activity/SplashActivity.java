package com.holepunchbatteryindicator.holepunchcameraeffects.activity;

import static com.holepunchbatteryindicator.holepunchcameraeffects.Application.adUnitId;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

public class SplashActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);


        UnityAds.load(adUnitId,new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAds.show(SplashActivity.this, adUnitId, new UnityAdsShowOptions(), null);
                if (GlobalPreferenceManager.isIntroscreenFinish()) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, IntroActivity.class));
                }
                finish();
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                Log.e("UnityAdsExample", "Unity Ads failed to load ad for " + placementId + " with error: [" + error + "] " + message);
            }
        });


    }



}





