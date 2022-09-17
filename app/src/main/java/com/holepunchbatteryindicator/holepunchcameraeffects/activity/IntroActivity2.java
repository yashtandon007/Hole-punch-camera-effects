package com.holepunchbatteryindicator.holepunchcameraeffects.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.fragment.BatteryOptPermissionSlide;
import com.holepunchbatteryindicator.holepunchcameraeffects.fragment.OverLayPermissionSlide;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;
import io.github.dreierf.materialintroscreen.animations.IViewTranslation;


public class IntroActivity2 extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_intro);
        Switch switchBattery = findViewById(R.id.switchBattery);
        Switch switchDisplay = findViewById(R.id.switchDisplay);

    }

}
