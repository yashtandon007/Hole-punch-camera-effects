package com.holepunchbatteryindicator.holepunchcameraeffects;

import android.content.Context;

import com.github.stkent.amplify.feedback.DefaultEmailFeedbackCollector;
import com.github.stkent.amplify.feedback.GooglePlayStoreFeedbackCollector;
import com.github.stkent.amplify.tracking.Amplify;
import com.github.stkent.amplify.tracking.rules.GooglePlayStoreRule;
import com.orhanobut.hawk.Hawk;

public class Application extends android.app.Application {
    static Application instance;

    public void onCreate() {
        super.onCreate();
      //  Utils.init(this);
        //LogUtils.getConfig().setLogSwitch(true).setConsoleSwitch(true).setGlobalTag(getString(R.string.logTag)).setBorderSwitch(true).setConsoleFilter(2);
        Hawk.init(getApplicationContext()).build();
        Amplify.initSharedInstance(this).setPositiveFeedbackCollectors(new GooglePlayStoreFeedbackCollector()).setCriticalFeedbackCollectors(new DefaultEmailFeedbackCollector("someone@example.com")).addEnvironmentBasedRule(new GooglePlayStoreRule());
//        MobileAds.initialize((Context) this, (OnInitializationCompleteListener) new OnInitializationCompleteListener() {
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
    }

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }
}
