package com.holepunchbatteryindicator.holepunchcameraeffects.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;
import com.holepunchbatteryindicator.holepunchcameraeffects.view.CenteredToolbar;
import com.github.stkent.amplify.prompt.DefaultLayoutPromptView;
import com.github.stkent.amplify.tracking.Amplify;


public class FeedbackActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_feedback);
        if (bundle == null) {
            Amplify.getSharedInstance().promptIfReady((DefaultLayoutPromptView) findViewById(R.id.promptView));
        }
        CenteredToolbar centeredToolbar = (CenteredToolbar) findViewById(R.id.toolbar);
        centeredToolbar.setTitleColor(getResources().getColor(R.color.colorWhite));
        centeredToolbar.setTitle((CharSequence) "FEEDBACK");
        centeredToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FeedbackActivity.this.onBackPressed();
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.banner);
        if (GlobalPreferenceManager.isAppPurchased()) {
            linearLayout.setVisibility(8);
            return;
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
//        linearLayout.addView(adView, layoutParams);
//        adView.loadAd(new AdRequest.Builder().build());
    }
}
