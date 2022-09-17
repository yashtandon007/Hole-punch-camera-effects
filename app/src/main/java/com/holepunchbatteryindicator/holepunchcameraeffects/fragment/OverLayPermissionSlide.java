package com.holepunchbatteryindicator.holepunchcameraeffects.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

import io.github.dreierf.materialintroscreen.SlideFragment;

public class OverLayPermissionSlide extends SlideFragment implements CompoundButton.OnCheckedChangeListener {
    private Switch permissionSwitch;

    public int backgroundColor() {
        return R.color.colorBlack;
    }

    public int buttonsColor() {
        return R.color.colorAccent;
    }

    public String cantMoveFurtherErrorMessage() {
        return "Please allow \"Display Over Other Apps\" permission";
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_custom_slide, viewGroup, false);
        Switch switchR = (Switch) inflate.findViewById(R.id.permissionSwitch);
        this.permissionSwitch = switchR;
        switchR.setOnCheckedChangeListener(this);
        return inflate;
    }

    public boolean canMoveFurther() {
        return Settings.canDrawOverlays(getActivity());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z && !GlobalPreferenceManager.isOverlayPermissionGranted()) {
            if (Build.VERSION.SDK_INT >= 23) {
                checkDrawOverlayPermission();
                return;
            }
          //  Toasty.info((Context) getActivity(), (CharSequence) "Your device is not suitable for this application", 0).show();
        }
    }

    public void checkDrawOverlayPermission() {
        if (!Settings.canDrawOverlays(getActivity())) {
            startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getActivity().getApplicationContext().getPackageName())), 1001);
            return;
        }
       // LogUtils.m14i("We already have permission for it.");
      //  Toasty.info((Context) getActivity(), (CharSequence) "We already have permission for it.", 0).show();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && Build.VERSION.SDK_INT >= 23 && Settings.canDrawOverlays(getActivity())) {
            GlobalPreferenceManager.setOverlayPermissionGranted(true);
        }
    }
}
