package com.holepunchbatteryindicator.holepunchcameraeffects.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

public class SplashActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (GlobalPreferenceManager.isIntroscreenFinish()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, IntroActivity.class));
        }
        finish();
    }
}
