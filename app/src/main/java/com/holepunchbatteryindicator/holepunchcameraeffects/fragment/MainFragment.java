package com.holepunchbatteryindicator.holepunchcameraeffects.fragment;

import static com.holepunchbatteryindicator.holepunchcameraeffects.Application.adUnitId;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.balysv.materialripple.BuildConfig;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.activity.SplashActivity;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;
import com.holepunchbatteryindicator.holepunchcameraeffects.service.OverlayService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;


public class MainFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    Fragment activeFragment;

    /* renamed from: fm */
    FragmentManager f71fm;
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;
//    private InterstitialAd interstitialAd;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            OverlayService unused = MainFragment.this.overlayService = ((OverlayService.LocalBinder) iBinder).getServiceInstance();
            boolean unused2 = MainFragment.this.mIsBound = true;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            OverlayService unused = MainFragment.this.overlayService = null;
            boolean unused2 = MainFragment.this.mIsBound = false;
        }
    };
    /* access modifiers changed from: private */
    public boolean mIsBound;
    /* access modifiers changed from: private */
    public OverlayService overlayService;
    private Intent serviceIntent;

    public static MainFragment newInstance() {
        return new MainFragment();
    }



    private void loadAdd() {
        Log.e("yash"," loadAdd");
        UnityAds.load(adUnitId,new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAds.show(getActivity(), adUnitId, new UnityAdsShowOptions(), null);
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                Log.e("UnityAdsExample", "Unity Ads failed to load ad for " + placementId + " with error: [" + error + "] " + message);
            }
        });

    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        int i = 0;
        View inflate = layoutInflater.inflate(R.layout.fragment_main, viewGroup, false);
        ((BottomNavigationView) inflate.findViewById(R.id.bottomNav)).setOnNavigationItemSelectedListener(this);
        FrameLayout frameLayout = inflate.findViewById(R.id.frameBanner);
        BannerView banner = new BannerView(getActivity(), "Banner_Android", new UnityBannerSize(320, 50));
        banner.load();
        frameLayout.addView(banner);
        loadAdd();
        this.fragment1 = new PositionFragment();
        this.fragment2 = new ColorFragment();
        this.fragment3 = new AnimationFragment();
        this.fragment4 = new ExtraFragment();
        this.activeFragment = this.fragment1;
        FragmentManager childFragmentManager = getChildFragmentManager();
        this.f71fm = childFragmentManager;
        childFragmentManager.beginTransaction().add(R.id.fragment, this.fragment4, BuildConfig.VERSION_NAME).hide(this.fragment4).commit();
        this.f71fm.beginTransaction().add(R.id.fragment, this.fragment3, "3").hide(this.fragment3).commit();
        this.f71fm.beginTransaction().add(R.id.fragment, this.fragment2, "2").hide(this.fragment2).commit();
        this.f71fm.beginTransaction().add(R.id.fragment, this.fragment1, "1").commit();
        int splashAdCount = GlobalPreferenceManager.getSplashAdCount();
        if (splashAdCount != 5) {
            i = splashAdCount + 1;
        }
        GlobalPreferenceManager.setSplashAdCount(i);
        if (!GlobalPreferenceManager.isAppPurchased()) {
            //TODO yash
//            InterstitialAd interstitialAd2 = new InterstitialAd(getActivity());
//            this.interstitialAd = interstitialAd2;
//            interstitialAd2.setAdUnitId("ca-app-pub-5326282618616655/5230310686");
//            this.interstitialAd.loadAd(new AdRequest.Builder().build());
//            this.interstitialAd.show();
        }
        if (!GlobalPreferenceManager.isBatteryPtPermissionGranted() && Build.VERSION.SDK_INT >= 23) {
            setBatteryOptimizationPermission();
        }
        return inflate;
    }

    public void setOverlayPosition(int i, int i2) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setOverlayPosition(i, i2);
        }
    }

    public int getOverlayPositionX() {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            return overlayService2.getOverlayPositionX();
        }
        return -30;
    }

    public int getOverlayPositionY() {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            return overlayService2.getOverlayPositionY();
        }
        return -30;
    }

    public void setShouldDrawDot(boolean z) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setShouldDrawDot(z);
        }
    }

    public void setDotRadius(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setDotRadius(i);
        }
    }

    public void setProgressBarCap(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setProgressBarCap(i);
        }
    }

    public void setProgressWithDashed(boolean z, float f) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setProgressWithDashed(z, f);
        }
    }

    public void setDashLength(float f) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setDashLength(f);
        }
    }

    public void setShowAnimation(boolean z) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setShowAnimation(z);
        }
    }

    public void setChargingAnimationType(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setChargingAnimationType(i);
        }
    }

    public void setColorConfigType(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setColorConfigType(i);
        }
    }

    public void setDotColor(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setDotColor(i);
        }
    }

    public void setProgressBackgroundColor(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setProgressBackgroundColor(i);
        }
    }

    public void setRingRadius(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setRingRadius(i);
        }
    }

    public void setProgressStrokeWidthDp(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setProgressStrokeWidthDp(i);
        }
    }

    public void setProgressBackgroundStrokeWidthDp(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setProgressBackgroundStrokeWidthDp(i);
        }
    }

    public void setStartAngle(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setStartAngle(i);
        }
    }

    public void setProgressDirection(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setProgressDirection(i);
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_animation:
                this.f71fm.beginTransaction().hide(this.activeFragment).show(this.fragment3).commit();
                this.activeFragment = this.fragment3;
                return true;
            case R.id.menu_color:
                this.f71fm.beginTransaction().hide(this.activeFragment).show(this.fragment2).commit();
                this.activeFragment = this.fragment2;
                GlobalPreferenceManager.getSplashAdCount();
                return true;
            case R.id.menu_extra:
                this.f71fm.beginTransaction().hide(this.activeFragment).show(this.fragment4).commit();
                this.activeFragment = this.fragment4;
                return true;
            case R.id.menu_settings:
                this.f71fm.beginTransaction().hide(this.activeFragment).show(this.fragment1).commit();
                this.activeFragment = this.fragment1;
                return true;
            default:
                return false;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.switch_menu, menu);
        MenuItem findItem = menu.findItem(R.id.menu_switch);
        findItem.setActionView(R.layout.layout_menu_switch);
        findItem.setVisible(true);
        SwitchCompat switchCompat = (SwitchCompat) findItem.getActionView().findViewById(R.id.switchOnOff);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                showAdd();
                if (!z) {
                    MainFragment.this.stopOverlayService();
                    GlobalPreferenceManager.setOverlayServiceStart(false);
                } else if (!GlobalPreferenceManager.isOverlayServiceStarted()) {
                    MainFragment.this.getActivity().startService(new Intent(MainFragment.this.getActivity(), OverlayService.class));
                    MainFragment.this.startOverlayService();
                    GlobalPreferenceManager.setOverlayServiceStart(true);
                }
            }
        });
        if (!GlobalPreferenceManager.isOverlayServiceStarted()) {
            switchCompat.setChecked(false);
        }

        else if (isMyServiceRunning((Class<?>) OverlayService.class)) {
            switchCompat.setChecked(true);
            GlobalPreferenceManager.setOverlayServiceStart(true);
        }
        else {
            switchCompat.setChecked(false);
            GlobalPreferenceManager.setOverlayServiceStart(false);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private void showAdd() {
        loadAdd();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void startOverlayService() {
        if (!this.mIsBound) {
            this.serviceIntent = new Intent(getActivity(), OverlayService.class);
            getActivity().bindService(this.serviceIntent, this.mConnection, 4);
        }
    }

    public void stopOverlayService() {
        try {
            if (this.serviceIntent != null) {
                getActivity().stopService(this.serviceIntent);
                if (this.mConnection != null) {
                    getActivity().unbindService(this.mConnection);
                    this.mIsBound = false;
                    return;
                }
                return;
            }
            //LogUtils.m14i("cannot stop");
        } catch (Exception e) {
            //LogUtils.m14i("Error: " + e);
        }
    }

    public void onResume() {
        super.onResume();
        if (GlobalPreferenceManager.isOverlayServiceStarted()) {
            startOverlayService();
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mIsBound) {
            try {
                if (this.mConnection != null && this.overlayService != null) {
                    getActivity().unbindService(this.mConnection);
                }
            } catch (Exception e) {
                //LogUtils.m14i("Error: " + e);
            }
        }
    }

    public void setOverlayProgress(int i) {
        OverlayService overlayService2 = this.overlayService;
        if (overlayService2 != null) {
            overlayService2.setProgress(i);
        }
    }

    public boolean checkBatteryOptimizationPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        String packageName = getActivity().getPackageName();
        FragmentActivity activity = getActivity();
        getActivity();
        if (!((PowerManager) activity.getSystemService("power")).isIgnoringBatteryOptimizations(packageName)) {
            return false;
        }
        return true;
    }

    public void setBatteryOptimizationPermission() {
        if (!checkBatteryOptimizationPermission()) {
            Intent intent = new Intent();
            intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
            startActivityForResult(intent, 1002);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1002 && i2 == -1 && Build.VERSION.SDK_INT >= 23) {
            GlobalPreferenceManager.setBatteryPtPermissionGranted(true);
        }
    }
}
