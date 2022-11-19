package com.holepunchbatteryindicator.holepunchcameraeffects;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.github.stkent.amplify.feedback.DefaultEmailFeedbackCollector;
import com.github.stkent.amplify.feedback.GooglePlayStoreFeedbackCollector;
import com.github.stkent.amplify.tracking.Amplify;
import com.github.stkent.amplify.tracking.rules.GooglePlayStoreRule;
import com.orhanobut.hawk.Hawk;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;

import java.util.Date;

public class Application extends android.app.Application  {
    //test comit
    public static Context context;
    Camera.Size[] f0A;
    int[] f1B = new int[4];
    String[] f2C;
    int[] f3D = new int[4];
    int f4E = 0;
    int f5F = 0;
    int f6G = 1;
    int f7H;
    boolean f8I = false;
    boolean f9J = true;
    float f10K = 0.0f;
    float f11L = 0.0f;
    float f12M = 0.0f;
    int f13N = 12;
    int f14O = 0;
    int f15P = 11;
    int f16Q = 0;
    int f17R = 0;
    int f18S = 5;
    int f19T = 4;
    int f20U = 128;
    int f21V = 5;
    int f22W = 0;
    int f23X = 0;
    String f24Y = null;
    int f25Z = 0;
    int f26a = 0;
    int aa = -1;
    int ab;
    float ac = 1.0f;
    float ad = 5.0f;
    int ae = 12;
    int af = 0;
    boolean ag = false;
    boolean f27b = true;
    boolean f28c = true;
    String[] f29d = new String[11];
    boolean f30e = true;
    boolean f31f = true;
    boolean f32g = true;
    boolean f33h = true;
    boolean f34i = true;
    boolean f35j = true;
    boolean f36k = true;
    int f37l = 0;
    boolean f38m = true;
    boolean f39n = true;
    boolean f40o = true;
    boolean f41p = true;
    boolean f42q = true;
    boolean f43r = true;
    boolean f44s = true;
    String f45t = null;
    boolean f46u = false;
    int f47v = 0;
    boolean f48w = true;
    int f49x;
    int f50y;
    int f51z;
    String gameID = "5027529";
    Boolean TESTMODE = false;
    public static String adUnitId = "Interstitial_Android";



    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Hawk.init(getApplicationContext()).build();
        Amplify.initSharedInstance(this).setPositiveFeedbackCollectors(new GooglePlayStoreFeedbackCollector()).setCriticalFeedbackCollectors(new DefaultEmailFeedbackCollector("someone@example.com")).addEnvironmentBasedRule(new GooglePlayStoreRule());
        UnityAds.initialize(this, gameID, TESTMODE, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                Log.e("unity","onInitializationComplete");
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {
                Log.e("unity","onInitializationFailed");

            }
        });

    }
}

