package com.holepunchbatteryindicator.holepunchcameraeffects.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.view.CenteredToolbar;

import mehdi.sakout.fancybuttons.FancyButton;

public class AboutActivity extends AppCompatActivity {
    private FancyButton btnPaidApp;
    private ImageView imgApp;
    private TextView tvVersion;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_about);
        CenteredToolbar centeredToolbar = (CenteredToolbar) findViewById(R.id.toolbar);
        centeredToolbar.setTitleColor(getResources().getColor(R.color.colorWhite));
        centeredToolbar.setTitle((CharSequence) "ABOUT");
        centeredToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AboutActivity.this.onBackPressed();
            }
        });
        this.tvVersion = (TextView) findViewById(R.id.tvVersion);
        this.imgApp = (ImageView) findViewById(R.id.imgApp);
        TextView textView = this.tvVersion;
        textView.setText("Version: AppUtils.getAppVersionName()" );
    }
}
