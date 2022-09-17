package com.holepunchbatteryindicator.holepunchcameraeffects.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.fragment.MainFragment;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;
import com.holepunchbatteryindicator.library.Model.HelpArin;
import com.holepunchbatteryindicator.library.interfaces.OnItemClickListener;
import com.holepunchbatteryindicator.library.navigationliveo.NavigationArin;


public class MainActivity extends NavigationArin implements OnItemClickListener {
    boolean isOnBootTrue = false;
    boolean isPurchaseQueryPending;
    private HelpArin mHelpArin;
    public TextView mToolbarTitle;

    public void onInt(Bundle bundle) {
        View inflate = getLayoutInflater().inflate(R.layout.drawer_header, getListView(), false);
        HelpArin helpArin = new HelpArin();
        this.mHelpArin = helpArin;
        helpArin.add(getString(R.string.battery), (int) R.drawable.ico_ringring);
        this.mHelpArin.add(getString(R.string.feedback), (int) R.drawable.ico_feedback);
        this.mHelpArin.add(getString(R.string.about), (int) R.drawable.ico_about);
        if (!GlobalPreferenceManager.isAppPurchased()) {
            this.mHelpArin.add(getString(R.string.buy), (int) R.drawable.ico_buy);
        }
        with(this).startingPosition(0).addAllHelpItem(this.mHelpArin.getHelp()).colorItemSelected(R.color.nliveo_blue_colorPrimary).colorItemDefault(R.color.colorWhite).customHeader(inflate).backgroundList(R.color.colorPrimary).build();
        setElevationToolBar(getCurrentPosition() != 2 ? 15.0f : 0.0f);
        View inflate2 = getLayoutInflater().inflate(R.layout.drawer_toolbar, (ViewGroup) null);
        this.mToolbarTitle = (TextView) inflate2.findViewById(R.id.toolbarTitle);
        getToolbar().addView(inflate2);
        setSupportActionBar(getToolbar());
        showRatingDialog();
        if (!GlobalPreferenceManager.isAppPurchased()) {
            //this.purchaseHelper = new PurchaseHelper(this, getPurchaseHelperListener());
        }
    }

    public void onItemClick(int i) {
        MainFragment mainFragment;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (i != 0) {
            if (i == 1) {
                startActivity(new Intent(this, FeedbackActivity.class));
                setCheckedItemNavigation(0, true);
            } else if (i == 2) {
                startActivity(new Intent(this, AboutActivity.class));
                setCheckedItemNavigation(0, true);
            } else if (i != 3) {
                getToolbar().setVisibility(0);
            }
            //TODO yash
//            else {
//                startActivity(new Intent(this, PurchaseActivity.class));
//                setCheckedItemNavigation(0, true);
//            }
            mainFragment = null;
        } else {
            this.mToolbarTitle.setText(getString(R.string.battery).toUpperCase());
            mainFragment = MainFragment.newInstance();
        }
        if (mainFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment).commit();
        }
        setElevationToolBar(i != 2 ? 15.0f : 0.0f);
    }

    private void showRatingDialog() {
        //TODO yash new AppRater.DefaultBuilder(this, getPackageName()).showDefault().daysToWait(3).timesToLaunch(4).title("Rate Notify me").notNowButton((String) null).rateButton("I want to rate").appLaunched();
    }



}
