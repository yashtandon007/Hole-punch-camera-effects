package com.holepunchbatteryindicator.holepunchcameraeffects.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;
import com.holepunchbatteryindicator.holepunchcameraeffects.view.circularProgressBar.CircularProgressIndicator;


public class OverlayService extends Service {
    private BatteryInfoBroadcastReceiver batteryInfoBroadcastReceiver;
    private CircularProgressIndicator circularProgressView;
    /* access modifiers changed from: private */
    public boolean isCharging = false;
    /* access modifiers changed from: private */
    public int level = -1;
    private IBinder mBinder;
    private WindowManager.LayoutParams mLP;
    private View mView;
    /* access modifiers changed from: private */
    public int scale = 100;
    /* access modifiers changed from: private */
    public int status = -1;
    private WindowManager windowManager;

    public void setChargingAnimationType(int i) {
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (this.batteryInfoBroadcastReceiver != null) {
            return 1;
        }
        BatteryInfoBroadcastReceiver batteryInfoBroadcastReceiver2 = new BatteryInfoBroadcastReceiver();
        this.batteryInfoBroadcastReceiver = batteryInfoBroadcastReceiver2;
        batteryInfoBroadcastReceiver2.setScreenUpdateService(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        registerReceiver(this.batteryInfoBroadcastReceiver, intentFilter);
        return 1;
    }

    public void onCreate() {
        super.onCreate();
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_overlay, (ViewGroup) null);
        this.mView = inflate;
        this.circularProgressView = (CircularProgressIndicator) inflate.findViewById(R.id.circularProgressView);
        LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.lLayout);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, Build.VERSION.SDK_INT >= 26 ? 2038 : 2002, 792, -2);
        this.mLP = layoutParams;
        layoutParams.gravity = 53;
        WindowManager windowManager2 = (WindowManager) getSystemService("window");
        this.windowManager = windowManager2;
        windowManager2.addView(this.mView, this.mLP);
        initialOverlaySetup();
    }

    public void setProgress(int i) {
        this.circularProgressView.setProgress((double) i, 100.0d);
    }

    public void onDestroy() {
        View view;
        WindowManager windowManager2 = this.windowManager;
        if (!(windowManager2 == null || (view = this.mView) == null)) {
            windowManager2.removeView(view);
        }
        try {
            if (this.batteryInfoBroadcastReceiver != null) {
                unregisterReceiver(this.batteryInfoBroadcastReceiver);
            }
        } catch (Exception e) {
        }
        super.onDestroy();
    }

    public static class BatteryInfoBroadcastReceiver extends BroadcastReceiver {
        private OverlayService overlayService;

        /* access modifiers changed from: package-private */
        public void setScreenUpdateService(OverlayService overlayService2) {
            this.overlayService = overlayService2;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (action != null && action.equals("android.intent.action.BATTERY_CHANGED")) {
                    int intExtra = intent.getIntExtra("level", 0);
                    int intExtra2 = intent.getIntExtra("status", 1);
                    if (this.overlayService.level != intExtra || this.overlayService.status != intExtra2) {
                        int unused = this.overlayService.scale = intent.getIntExtra("scale", 100);
                        int unused2 = this.overlayService.level = intExtra;
                        int unused3 = this.overlayService.status = intExtra2;
                        if (intExtra2 == 2) {
                            boolean unused4 = this.overlayService.isCharging = true;
                        } else {
                            boolean unused5 = this.overlayService.isCharging = false;
                        }
                        if (this.overlayService.scale != 100) {
                            if (this.overlayService.scale <= 0) {
                                int unused6 = this.overlayService.scale = 100;
                            }
                            int unused7 = this.overlayService.level = (this.overlayService.level * 100) / this.overlayService.scale;
                        }
                        if (GlobalPreferenceManager.getColorConfigType() == 0) {
                            this.overlayService.setProgressColor(GlobalPreferenceManager.getBtn1SingleColor());
                        } else if (GlobalPreferenceManager.getColorConfigType() == 1) {
                            if (this.overlayService.level <= 25) {
                                this.overlayService.setProgressColor(GlobalPreferenceManager.getBtn1SegColor());
                            } else if (this.overlayService.level > 25 && this.overlayService.level <= 50) {
                                this.overlayService.setProgressColor(GlobalPreferenceManager.getBtn2SegColor());
                            } else if (this.overlayService.level > 50 && this.overlayService.level <= 75) {
                                this.overlayService.setProgressColor(GlobalPreferenceManager.getBtn3SegColor());
                            } else if (this.overlayService.level > 75 && this.overlayService.level <= 100) {
                                this.overlayService.setProgressColor(GlobalPreferenceManager.getBtn4SegColor());
                            }
                        } else if (GlobalPreferenceManager.getColorConfigType() == 2) {
                            this.overlayService.setProgressGradientColor(GlobalPreferenceManager.getBtn1GradColor(), GlobalPreferenceManager.getBtn2GradColor());
                        }
                        this.overlayService.updateFromBatteryBoardCast();
                    }
                }
            } catch (Exception e) {
                //LogUtils.m14i("onReceive", e);
            }
        }
    }

    public void updateFromBatteryBoardCast() {
        //LogUtils.m14i("I m " + this.isCharging);
        if (this.isCharging) {
            this.circularProgressView.setChargingAnimation(true);
            return;
        }
        this.circularProgressView.setChargingAnimation(false);
        this.circularProgressView.setProgress((double) this.level, 100.0d);
    }

    public IBinder onBind(Intent intent) {
        if (this.mBinder == null) {
            this.mBinder = new LocalBinder();
        }
        return this.mBinder;
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public OverlayService getServiceInstance() {
            return OverlayService.this;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.circularProgressView.setVisibility(4);
        } else {
            this.circularProgressView.setVisibility(0);
        }
    }

    public void setShouldDrawDot(boolean z) {
        this.circularProgressView.setShouldDrawDot(z);
        this.circularProgressView.setDotWidthDp(GlobalPreferenceManager.getDotRadius());
    }

    public void setDotRadius(int i) {
        this.circularProgressView.setDotWidthDp(i);
    }

    public void setProgressBarCap(int i) {
        this.circularProgressView.setProgressStrokeCap(i);
    }

    public void setProgressWithDashed(boolean z, float f) {
        this.circularProgressView.setProgressWithDashed(z, f);
    }

    public void setDashLength(float f) {
        this.circularProgressView.setProgressWithDashed(GlobalPreferenceManager.isOverlayDashed(), f);
    }

    public void setShowAnimation(boolean z) {
        this.circularProgressView.showChargingAnimation(z);
    }

    public void setProgressColor(int i) {
        this.circularProgressView.setProgressColor(i);
    }

    public void setProgressGradientColor(int i, int i2) {
        this.circularProgressView.setGradient(i, i2);
    }

    public void setColorConfigType(int i) {
        if (i == 0) {
            this.circularProgressView.setGradient(0, ViewCompat.MEASURED_STATE_MASK);
            setProgressColor(GlobalPreferenceManager.getBtn1SingleColor());
        } else if (i == 1) {
            this.circularProgressView.setGradient(0, ViewCompat.MEASURED_STATE_MASK);
            int i2 = this.level;
            if (i2 <= 25) {
                setProgressColor(GlobalPreferenceManager.getBtn1SegColor());
            } else if (i2 <= 25 || i2 > 50) {
                int i3 = this.level;
                if (i3 <= 50 || i3 > 75) {
                    int i4 = this.level;
                    if (i4 > 75 && i4 <= 100) {
                        setProgressColor(GlobalPreferenceManager.getBtn4SegColor());
                        return;
                    }
                    return;
                }
                setProgressColor(GlobalPreferenceManager.getBtn3SegColor());
            } else {
                setProgressColor(GlobalPreferenceManager.getBtn2SegColor());
            }
        } else if (i == 2) {
            setProgressGradientColor(GlobalPreferenceManager.getBtn1GradColor(), GlobalPreferenceManager.getBtn2GradColor());
        }
    }

    public void setDotColor(int i) {
        this.circularProgressView.setDotColor(i);
    }

    public void setProgressBackgroundColor(int i) {
        this.circularProgressView.setProgressBackgroundColor(i);
    }

    public void setRingRadius(int i) {
        this.circularProgressView.setLayoutParams(new LinearLayout.LayoutParams(i, i));
    }

    public void setProgressStrokeWidthDp(int i) {
        this.circularProgressView.setProgressStrokeWidthDp(i);
    }

    public void setProgressBackgroundStrokeWidthDp(int i) {
        this.circularProgressView.setProgressBackgroundStrokeWidthDp(i);
    }

    public void setStartAngle(int i) {
        this.circularProgressView.setStartAngle(i);
    }

    public void setProgressDirection(int i) {
        if (i == 0) {
            this.circularProgressView.setDirection(0);
            CircularProgressIndicator circularProgressIndicator = this.circularProgressView;
            circularProgressIndicator.setProgress(circularProgressIndicator.getProgress(), 100.0d);
        } else if (i == 1) {
            this.circularProgressView.setDirection(1);
            CircularProgressIndicator circularProgressIndicator2 = this.circularProgressView;
            circularProgressIndicator2.setProgress(circularProgressIndicator2.getProgress(), 100.0d);
        }
    }

    public void setOverlayPosition(int i, int i2) {
        this.mLP.x = i;
        this.mLP.y = i2;
        this.windowManager.updateViewLayout(this.mView, this.mLP);
    }

    public int getOverlayPositionX() {
        return this.mLP.x;
    }

    public int getOverlayPositionY() {
        return this.mLP.y;
    }

    public void initialOverlaySetup() {
        if (GlobalPreferenceManager.isDotEnabled()) {
            setShouldDrawDot(true);
            setDotRadius(GlobalPreferenceManager.getDotRadius());
        } else {
            setShouldDrawDot(false);
        }
        this.circularProgressView.setProgressStrokeCap(GlobalPreferenceManager.getProgressBarCap());
        setProgressWithDashed(GlobalPreferenceManager.isOverlayDashed(), GlobalPreferenceManager.getDashLength());
        setColorConfigType(GlobalPreferenceManager.getColorConfigType());
        setDotColor(GlobalPreferenceManager.getDotColor());
        setProgressBackgroundColor(GlobalPreferenceManager.getOuterRingColor());
        setRingRadius(GlobalPreferenceManager.getRingRadius());
        setProgressStrokeWidthDp(GlobalPreferenceManager.getInnerBarThickness());
        setProgressBackgroundStrokeWidthDp(GlobalPreferenceManager.getOutBarThickness());
        setStartAngle(GlobalPreferenceManager.getStartAngle());
        setProgressDirection(GlobalPreferenceManager.getProgressDirection());
        setOverlayPosition(GlobalPreferenceManager.getPositionX(), GlobalPreferenceManager.getPositionY());
    }
}
