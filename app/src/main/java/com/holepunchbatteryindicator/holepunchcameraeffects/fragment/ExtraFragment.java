package com.holepunchbatteryindicator.holepunchcameraeffects.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ExtraFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private MaterialSpinner dropdownCap;
    MainFragment mainFragment;
    private SeekBar seekbarGapSize;
    private SeekBar seekbarRadius;
    private Switch switchDash;
    private Switch switchDot;

    public static ExtraFragment newInstance() {
        return new ExtraFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(R.layout.fragment_extra, viewGroup, false);
        this.dropdownCap = (MaterialSpinner) inflate.findViewById(R.id.dropdownCap);
        Switch switchR = (Switch) inflate.findViewById(R.id.switchDot);
        this.switchDot = switchR;
        switchR.setOnCheckedChangeListener(this);
        Switch switchR2 = (Switch) inflate.findViewById(R.id.switchDash);
        this.switchDash = switchR2;
        switchR2.setOnCheckedChangeListener(this);
        SeekBar seekBar = (SeekBar) inflate.findViewById(R.id.seekbarRadius);
        this.seekbarRadius = seekBar;
        seekBar.setProgress(GlobalPreferenceManager.getDotRadius());
        this.seekbarRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                ExtraFragment.this.mainFragment.setDotRadius(i);
                GlobalPreferenceManager.setDotRadius(i);
            }
        });
        SeekBar seekBar2 = (SeekBar) inflate.findViewById(R.id.seekbarGapSize);
        this.seekbarGapSize = seekBar2;
        seekBar2.setProgress((int) GlobalPreferenceManager.getDashLength());
        this.seekbarGapSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                float f = (float) i;
                ExtraFragment.this.mainFragment.setDashLength(f);
                GlobalPreferenceManager.setDashLength(f);
            }
        });
        setupDropdownAnimation();
        this.mainFragment = (MainFragment) getParentFragment();
        if (GlobalPreferenceManager.isDotEnabled()) {
            this.switchDot.setChecked(true);
        } else {
            this.switchDot.setChecked(false);
        }
        if (GlobalPreferenceManager.isOverlayDashed()) {
            this.switchDash.setChecked(true);
        } else {
            this.switchDash.setChecked(false);
        }
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.bannerLayout);
        if (!GlobalPreferenceManager.isAppPurchased()) {
            linearLayout.setVisibility(0);
            //TODO yash
//            AdView adView = new AdView(getActivity());
//            adView.setAdSize(AdSize.BANNER);
//            adView.setAdUnitId("ca-app-pub-5326282618616655/1977461380");
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
//            layoutParams.gravity = 17;
//            linearLayout.addView(adView, layoutParams);
//            adView.loadAd(new AdRequest.Builder().build());
        } else {
            linearLayout.setVisibility(8);
        }
        return inflate;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == this.switchDot) {
            if (z) {
                if (!GlobalPreferenceManager.isDotEnabled()) {
                    this.mainFragment.setShouldDrawDot(true);
                    GlobalPreferenceManager.setShouldDrawDot(true);
                }
            } else if (GlobalPreferenceManager.isDotEnabled()) {
                this.mainFragment.setShouldDrawDot(false);
                GlobalPreferenceManager.setShouldDrawDot(false);
            }
        } else if (compoundButton != this.switchDash) {
        } else {
            if (z) {
                if (!GlobalPreferenceManager.isOverlayDashed()) {
                    this.mainFragment.setProgressWithDashed(true, GlobalPreferenceManager.getDashLength());
                    GlobalPreferenceManager.setOverlayDash(true);
                }
            } else if (GlobalPreferenceManager.isOverlayDashed()) {
                this.mainFragment.setProgressWithDashed(false, GlobalPreferenceManager.getDashLength());
                GlobalPreferenceManager.setOverlayDash(false);
            }
        }
    }

    public void setupDropdownAnimation() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item_layout, getResources().getStringArray(R.array.capTypeList));
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_overlay_item_layout);
        this.dropdownCap.setAdapter((SpinnerAdapter) arrayAdapter);
        this.dropdownCap.setSelection(GlobalPreferenceManager.getProgressBarCap());
        this.dropdownCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    ExtraFragment.this.mainFragment.setProgressBarCap(0);
                    GlobalPreferenceManager.setProgressBarCap(0);
                } else if (i == 1) {
                    ExtraFragment.this.mainFragment.setProgressBarCap(1);
                    GlobalPreferenceManager.setProgressBarCap(1);
                }
            }
        });
    }
}
