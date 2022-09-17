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
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.orhanobut.hawk.Hawk;

import java.util.Date;

public class Application extends android.app.Application implements android.app.Application.ActivityLifecycleCallbacks, LifecycleObserver {
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

    private AppOpenAdManager appOpenAdManager;
    private Activity currentActivity;

    /**
     * Interface definition for a callback to be invoked when an app open ad is complete.
     */
    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }

    /**
     * ActivityLifecycleCallback methods.
     */
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        // Updating the currentActivity only when an ad is not showing.
        if (!appOpenAdManager.isShowingAd) {
            currentActivity = activity;
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Hawk.init(getApplicationContext()).build();
        Amplify.initSharedInstance(this).setPositiveFeedbackCollectors(new GooglePlayStoreFeedbackCollector()).setCriticalFeedbackCollectors(new DefaultEmailFeedbackCollector("someone@example.com")).addEnvironmentBasedRule(new GooglePlayStoreRule());

        this.registerActivityLifecycleCallbacks(this);
        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        appOpenAdManager = new AppOpenAdManager();

    }

    public void showAdIfAvailable(
            @NonNull Activity activity,
            @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
        // We wrap the showAdIfAvailable to enforce that other classes only interact with MyApplication
        // class.
        appOpenAdManager.showAdIfAvailable(activity, onShowAdCompleteListener);
    }


    /**
     * LifecycleObserver method that shows the app open ad when the app moves to foreground.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onMoveToForeground() {
        // Show the ad (if available) when the app moves to foreground.
        appOpenAdManager.showAdIfAvailable(currentActivity, new OnShowAdCompleteListener() {
            @Override
            public void onShowAdComplete() {
                // Empty because the user will go back to the activity that shows the ad.
            }
        });
    }


    /**
     * Inner class that loads and shows app open ads.
     */
    private class AppOpenAdManager {
        private static final String LOG_TAG = "AppOpenAdManager";
        private final String AD_UNIT_ID = Application.this.getApplicationContext().getString(R.string.appopen);
        long loadTime = 0;
        private AppOpenAd appOpenAd = null;
        private boolean isLoadingAd = false;
        private boolean isShowingAd = false;

        public void showAdIfAvailable(
                @NonNull final Activity activity,
                @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
            // If the app open ad is already showing, do not show the ad again.
            if (isShowingAd) {
                Log.d(AppOpenAdManager.LOG_TAG, "The app open ad is already showing.");
                return;
            }

            // If the app open ad is not available yet, invoke the callback then load the ad.
            if (!isAdAvailable()) {
                Log.d(AppOpenAdManager.LOG_TAG, "The app open ad is not ready yet.");
                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
                return;
            }

            appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    // Set the reference to null so isAdAvailable() returns false.
                    Log.d(LOG_TAG, "Ad dismissed fullscreen content.");
                    appOpenAd = null;
                    isShowingAd = false;

                    onShowAdCompleteListener.onShowAdComplete();
                    loadAd(activity);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show.
                    // Set the reference to null so isAdAvailable() returns false.
                    Log.d(LOG_TAG, adError.getMessage());
                    appOpenAd = null;
                    isShowingAd = false;

                    onShowAdCompleteListener.onShowAdComplete();
                    loadAd(activity);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    Log.d(LOG_TAG, "Ad showed fullscreen content.");
                }
            });
            isShowingAd = true;
            appOpenAd.show(activity);
        }

        /**
         * Constructor.
         */
        public AppOpenAdManager() {
        }

        /**
         * Request an ad.
         */
        public void loadAd(Context context) {
            // Do not load ad if there is an unused ad or one is already loading.
            if (isLoadingAd || isAdAvailable()) {
                return;
            }

            isLoadingAd = true;
            AdRequest request = new AdRequest.Builder().build();
            AppOpenAd.load(
                    context, AD_UNIT_ID, request,
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                    new AppOpenAd.AppOpenAdLoadCallback() {
                        @Override
                        public void onAdLoaded(AppOpenAd ad) {
                            // Called when an app open ad has loaded.
                            Log.d(LOG_TAG, "Ad was loaded.");
                            appOpenAd = ad;
                            isLoadingAd = false;
                            loadTime = (new Date()).getTime();
                        }

                        @Override
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            // Called when an app open ad has failed to load.
                            Log.d(LOG_TAG, loadAdError.getMessage());
                            isLoadingAd = false;
                        }
                    });
        }
        /** Check if ad exists and can be shown. */
        /**
         * Utility method to check if ad was loaded more than n hours ago.
         */
        private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
            long dateDifference = (new Date()).getTime() - this.loadTime;
            long numMilliSecondsPerHour = 3600000;
            return (dateDifference < (numMilliSecondsPerHour * numHours));
        }

        /**
         * Check if ad exists and can be shown.
         */
        public boolean isAdAvailable() {
            return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
        }
    }


}

//    public void onCreate() {
//        super.onCreate();
//
//

//    }
//
//}
