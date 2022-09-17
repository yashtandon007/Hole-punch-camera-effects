package com.holepunchbatteryindicator.holepunchcameraeffects.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.fragment.BatteryOptPermissionSlide;
import com.holepunchbatteryindicator.holepunchcameraeffects.fragment.OverLayPermissionSlide;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;
import io.github.dreierf.materialintroscreen.animations.IViewTranslation;


public class IntroActivity extends MaterialIntroActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        enableLastSlideAlphaExitTransition(true);
        getBackButtonTranslationWrapper().setEnterTranslation(new IViewTranslation() {
            public void translate(View view, float f) {
                view.setAlpha(f);
            }
        });
        addSlide(new OverLayPermissionSlide());
        addSlide(new BatteryOptPermissionSlide());
        addSlide(new SlideFragmentBuilder().image(R.drawable.ic_finish).backgroundColor(R.color.colorGreen).buttonsColor(R.color.okButtonColor).title("That's it").build());
    }

    public void onFinish() {
        super.onFinish();
        GlobalPreferenceManager.setIntroScreenFinish(true);
        startActivity(new Intent(this, MainActivity.class));
    }

}
