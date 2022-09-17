package com.holepunchbatteryindicator.holepunchcameraeffects.fragment;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.fragment.app.FragmentActivity;

import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

import io.github.dreierf.materialintroscreen.SlideFragment;


public class BatteryOptPermissionSlide extends SlideFragment implements CompoundButton.OnCheckedChangeListener {
    private Switch permissionSwitch;

    public int backgroundColor() {
        return R.color.colorBlack;
    }

    public int buttonsColor() {
        return R.color.colorAccent;
    }

    public String cantMoveFurtherErrorMessage() {
        return "Please allow \"Battery Optimization\" permission";
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_batteryop_slide, viewGroup, false);
        Switch switchR = (Switch) inflate.findViewById(R.id.permissionSwitch);
        this.permissionSwitch = switchR;
        switchR.setOnCheckedChangeListener(this);
        return inflate;
    }

    public boolean canMoveFurther() {
        return checkBatteryOptimizationPermission();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z && !GlobalPreferenceManager.isBatteryPtPermissionGranted() && Build.VERSION.SDK_INT >= 23) {
            setBatteryOptimizationPermission();
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
        Log.e("yash", "setBatteryOptimizationPermission: setBatteryOptimizationPermission");
        if (!checkBatteryOptimizationPermission()) {

            Log.e("yash", "setBatteryOptimizationPermission: setBatteryOptimizationPermission  if ");
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity"));
                intent.putExtra("package_name", getContext().getPackageName());
                intent.putExtra("package_label", getText(R.string.app_name));
                startActivity(intent);
            } catch (ActivityNotFoundException anfe) {

                Intent intent = new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS);
                intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
                startActivityForResult(intent, 1002);

            }


        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1002 && i2 == -1 && Build.VERSION.SDK_INT >= 23) {
            GlobalPreferenceManager.setBatteryPtPermissionGranted(true);
        }
    }
}
