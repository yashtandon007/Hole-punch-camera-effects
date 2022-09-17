package com.holepunchbatteryindicator.holepunchcameraeffects.activity;

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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

public class SplashActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public static InterstitialAd mInterstitialAdSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.e("yash", "add initialised");
                loadAdd();
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("yash"," show........");

                // Show the app open ad.
                Application application = getApplication();

                ((com.holepunchbatteryindicator.holepunchcameraeffects.Application) application)
                        .showAdIfAvailable(
                                SplashActivity.this,
                                new com.holepunchbatteryindicator.holepunchcameraeffects.Application.OnShowAdCompleteListener() {
                                    @Override
                                    public void onShowAdComplete() {
                                        if (GlobalPreferenceManager.isIntroscreenFinish()) {
                                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                        } else {
                                            startActivity(new Intent(SplashActivity.this, IntroActivity.class));
                                        }
                                        finish();
                                    }
                                });
            }
        }, 4000);

    }

    private void loadAdd() {
        Log.e("yash", " loadAdd");
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(SplashActivity.this, SplashActivity.this.getResources().getString(R.string.intersticial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                Log.e("yash", " loaded............");
                mInterstitialAdSplash = interstitialAd;

            }

        });

    }


}





