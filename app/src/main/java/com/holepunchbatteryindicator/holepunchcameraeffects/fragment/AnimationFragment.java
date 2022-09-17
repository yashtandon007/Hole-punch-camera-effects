package com.holepunchbatteryindicator.holepunchcameraeffects.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

import fr.ganfra.materialspinner.MaterialSpinner;


public class AnimationFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private MaterialSpinner dropdownAnimation;
    MainFragment mainFragment;
    private Switch switchAnimation;

    public static AnimationFragment newInstance() {
        return new AnimationFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(R.layout.fragment_animation, viewGroup, false);
        this.dropdownAnimation = (MaterialSpinner) inflate.findViewById(R.id.dropdownAnimation);
        setupDropdownAnimation();
        this.mainFragment = (MainFragment) getParentFragment();
        this.dropdownAnimation.setSelection(GlobalPreferenceManager.getChargingAnimationType());
        Switch switchR = (Switch) inflate.findViewById(R.id.switchAnimation);
        this.switchAnimation = switchR;
        switchR.setOnCheckedChangeListener(this);
        if (GlobalPreferenceManager.isAnimationEnabled()) {
            this.switchAnimation.setChecked(true);
        } else {
            this.switchAnimation.setChecked(false);
        }
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.bannerLayout);
        if (!GlobalPreferenceManager.isAppPurchased()) {
            linearLayout.setVisibility(0);
            //TODO yash
//            AdView adView = new AdView(getActivity());
//            adView.setAdSize(AdSize.BANNER);
//            adView.setAdUnitId("ca-app-pub-5326282618616655/8840105404");
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
//            layoutParams.gravity = 17;
//            linearLayout.addView(adView, layoutParams);
//            adView.loadAd(new AdRequest.Builder().build());
        } else {
            linearLayout.setVisibility(8);
        }
        return inflate;
    }

    public void setupDropdownAnimation() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item_layout, getResources().getStringArray(R.array.animationList));
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_overlay_item_layout);
        this.dropdownAnimation.setAdapter((SpinnerAdapter) arrayAdapter);
        this.dropdownAnimation.setSelection(GlobalPreferenceManager.getChargingAnimationType());
        this.dropdownAnimation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    AnimationFragment.this.mainFragment.setChargingAnimationType(0);
                    GlobalPreferenceManager.setChargingAnimationType(0);
                } else if (i == 1) {
                    AnimationFragment.this.mainFragment.setChargingAnimationType(1);
                    GlobalPreferenceManager.setChargingAnimationType(1);
                } else if (i == 2) {
                    AnimationFragment.this.mainFragment.setChargingAnimationType(2);
                    GlobalPreferenceManager.setChargingAnimationType(2);
                } else if (i == 3) {
                    AnimationFragment.this.mainFragment.setChargingAnimationType(3);
                    GlobalPreferenceManager.setChargingAnimationType(3);
                }
            }
        });
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton != this.switchAnimation) {
            return;
        }
        if (z) {
            if (!GlobalPreferenceManager.isAnimationEnabled()) {
                GlobalPreferenceManager.setShowAnimation(true);
                this.mainFragment.setShowAnimation(true);
            }
        } else if (GlobalPreferenceManager.isAnimationEnabled()) {
            GlobalPreferenceManager.setShowAnimation(false);
            this.mainFragment.setShowAnimation(false);
        }
    }
}
