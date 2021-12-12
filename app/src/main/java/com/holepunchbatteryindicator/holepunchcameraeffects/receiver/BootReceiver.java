package com.holepunchbatteryindicator.holepunchcameraeffects.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.holepunchbatteryindicator.holepunchcameraeffects.activity.MainActivity;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) && GlobalPreferenceManager.isOverlayServiceStarted()) {
            Intent intent2 = new Intent(context, MainActivity.class);
            intent2.putExtra("ON_BOOT", true);
            context.startActivity(intent2);
        }
    }
}
