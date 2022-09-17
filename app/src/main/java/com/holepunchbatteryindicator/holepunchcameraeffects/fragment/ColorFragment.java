package com.holepunchbatteryindicator.holepunchcameraeffects.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;

import fr.ganfra.materialspinner.MaterialSpinner;
import me.jfenn.colorpickerdialog.dialogs.ColorPickerDialog;
import me.jfenn.colorpickerdialog.interfaces.OnColorPickedListener;
import mehdi.sakout.fancybuttons.FancyButton;

public class ColorFragment extends Fragment implements View.OnClickListener {
    /* access modifiers changed from: private */
    public FancyButton btn1;
    /* access modifiers changed from: private */
    public int btn1Color;
    /* access modifiers changed from: private */
    public FancyButton btn2;
    /* access modifiers changed from: private */
    public int btn2Color;
    /* access modifiers changed from: private */
    public FancyButton btn3;
    /* access modifiers changed from: private */
    public int btn3Color;
    /* access modifiers changed from: private */
    public FancyButton btn4;
    /* access modifiers changed from: private */
    public int btn4Color;
    /* access modifiers changed from: private */
    public FancyButton btnDot;
    /* access modifiers changed from: private */
    public int btnDotColor;
    /* access modifiers changed from: private */
    public FancyButton btnOuterRing;
    /* access modifiers changed from: private */
    public int btnOuterRingColor;
    private MaterialSpinner dropdownColorConfig;
    MainFragment mainFragment;

    public static ColorFragment newInstance() {
        return new ColorFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(R.layout.fragment_color, viewGroup, false);
        this.dropdownColorConfig = (MaterialSpinner) inflate.findViewById(R.id.dropdownColorConfig);
        this.btn1 = (FancyButton) inflate.findViewById(R.id.btn1);
        this.btn2 = (FancyButton) inflate.findViewById(R.id.btn2);
        this.btn3 = (FancyButton) inflate.findViewById(R.id.btn3);
        this.btn4 = (FancyButton) inflate.findViewById(R.id.btn4);
        this.btnOuterRing = (FancyButton) inflate.findViewById(R.id.btnOuterRingColor);
        this.btnDot = (FancyButton) inflate.findViewById(R.id.btnDotColor);
        this.btn1.setOnClickListener(this);
        this.btn2.setOnClickListener(this);
        this.btn3.setOnClickListener(this);
        this.btn4.setOnClickListener(this);
        this.btnOuterRing.setOnClickListener(this);
        this.btnDot.setOnClickListener(this);
        this.mainFragment = (MainFragment) getParentFragment();
        setupDropdownColorConfig();
        this.btnOuterRingColor = GlobalPreferenceManager.getOuterRingColor();
        this.btnOuterRing.setBackgroundColor(GlobalPreferenceManager.getOuterRingColor());
        this.btnDotColor = GlobalPreferenceManager.getDotColor();
        this.btnDot.setBackgroundColor(GlobalPreferenceManager.getDotColor());
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.bannerLayout);
        if (!GlobalPreferenceManager.isAppPurchased()) {
            linearLayout.setVisibility(0);
//            AdView adView = new AdView(getActivity());
//            adView.setAdSize(AdSize.BANNER);
//            adView.setAdUnitId("ca-app-pub-5326282618616655/1079312682");
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
//            layoutParams.gravity = 17;
//            linearLayout.addView(adView, layoutParams);
//            adView.loadAd(new AdRequest.Builder().build());
        } else {
            linearLayout.setVisibility( 8);
        }
        return inflate;
    }

    public void onClick(View view) {
        if (view == this.btn1) {
            ((ColorPickerDialog) ((ColorPickerDialog) new ColorPickerDialog().withColor(this.btn1Color)).withPresets(-1, InputDeviceCompat.SOURCE_ANY, -16711681, SupportMenu.CATEGORY_MASK, -65281, -16711936, -12303292, -16776961, ViewCompat.MEASURED_STATE_MASK, -3355444).withListener(new OnColorPickedListener<ColorPickerDialog>() {
                public void onColorPicked(ColorPickerDialog colorPickerDialog, int i) {
                    ColorFragment.this.btn1.setBackgroundColor(i);
                    int unused = ColorFragment.this.btn1Color = i;
                    if (GlobalPreferenceManager.getColorConfigType() == 0) {
                        GlobalPreferenceManager.setBtn1SingleColor(i);
                        ColorFragment.this.mainFragment.setColorConfigType(GlobalPreferenceManager.getColorConfigType());
                    } else if (GlobalPreferenceManager.getColorConfigType() == 1) {
                        GlobalPreferenceManager.setBtn1SegColor(i);
                        ColorFragment.this.mainFragment.setColorConfigType(GlobalPreferenceManager.getColorConfigType());
                    } else if (GlobalPreferenceManager.getColorConfigType() == 2) {
                        GlobalPreferenceManager.setBtn1GradColor(i);
                        ColorFragment.this.mainFragment.setColorConfigType(GlobalPreferenceManager.getColorConfigType());
                    }
                }
            })).show(getChildFragmentManager(), "colorPicker");
        } else if (view == this.btn2) {
            ((ColorPickerDialog) ((ColorPickerDialog) new ColorPickerDialog().withColor(this.btn2Color)).withPresets(-1, InputDeviceCompat.SOURCE_ANY, -16711681, SupportMenu.CATEGORY_MASK, -65281, -16711936, -12303292, -16776961, ViewCompat.MEASURED_STATE_MASK, -3355444).withListener(new OnColorPickedListener<ColorPickerDialog>() {
                public void onColorPicked(ColorPickerDialog colorPickerDialog, int i) {
                    ColorFragment.this.btn2.setBackgroundColor(i);
                    int unused = ColorFragment.this.btn2Color = i;
                    if (GlobalPreferenceManager.getColorConfigType() == 1) {
                        GlobalPreferenceManager.setBtn2SegColor(i);
                        ColorFragment.this.mainFragment.setColorConfigType(GlobalPreferenceManager.getColorConfigType());
                    } else if (GlobalPreferenceManager.getColorConfigType() == 2) {
                        GlobalPreferenceManager.setBtn2GradColor(i);
                        ColorFragment.this.mainFragment.setColorConfigType(GlobalPreferenceManager.getColorConfigType());
                    }
                }
            })).show(getChildFragmentManager(), "colorPicker");
        } else if (view == this.btn3) {
            ((ColorPickerDialog) ((ColorPickerDialog) new ColorPickerDialog().withColor(this.btn3Color)).withPresets(-1, InputDeviceCompat.SOURCE_ANY, -16711681, SupportMenu.CATEGORY_MASK, -65281, -16711936, -12303292, -16776961, ViewCompat.MEASURED_STATE_MASK, -3355444).withListener(new OnColorPickedListener<ColorPickerDialog>() {
                public void onColorPicked(ColorPickerDialog colorPickerDialog, int i) {
                    ColorFragment.this.btn3.setBackgroundColor(i);
                    int unused = ColorFragment.this.btn3Color = i;
                    if (GlobalPreferenceManager.getColorConfigType() == 1) {
                        GlobalPreferenceManager.setBtn3SegColor(i);
                        ColorFragment.this.mainFragment.setColorConfigType(GlobalPreferenceManager.getColorConfigType());
                    }
                }
            })).show(getChildFragmentManager(), "colorPicker");
        } else if (view == this.btn4) {
            ((ColorPickerDialog) ((ColorPickerDialog) new ColorPickerDialog().withColor(this.btn4Color)).withPresets(-1, InputDeviceCompat.SOURCE_ANY, -16711681, SupportMenu.CATEGORY_MASK, -65281, -16711936, -12303292, -16776961, ViewCompat.MEASURED_STATE_MASK, -3355444).withListener(new OnColorPickedListener<ColorPickerDialog>() {
                public void onColorPicked(ColorPickerDialog colorPickerDialog, int i) {
                    ColorFragment.this.btn4.setBackgroundColor(i);
                    int unused = ColorFragment.this.btn4Color = i;
                    if (GlobalPreferenceManager.getColorConfigType() == 1) {
                        GlobalPreferenceManager.setBtn4SegColor(i);
                        ColorFragment.this.mainFragment.setColorConfigType(GlobalPreferenceManager.getColorConfigType());
                    }
                }
            })).show(getChildFragmentManager(), "colorPicker");
        } else if (view == this.btnOuterRing) {
            ((ColorPickerDialog) ((ColorPickerDialog) new ColorPickerDialog().withColor(this.btnOuterRingColor)).withPresets(-1, InputDeviceCompat.SOURCE_ANY, -16711681, SupportMenu.CATEGORY_MASK, -65281, -16711936, -12303292, -16776961, ViewCompat.MEASURED_STATE_MASK, -3355444).withListener(new OnColorPickedListener<ColorPickerDialog>() {
                public void onColorPicked(ColorPickerDialog colorPickerDialog, int i) {
                    ColorFragment.this.btnOuterRing.setBackgroundColor(i);
                    int unused = ColorFragment.this.btnOuterRingColor = i;
                    GlobalPreferenceManager.setOuterRingColor(i);
                    ColorFragment.this.mainFragment.setProgressBackgroundColor(i);
                }
            })).show(getChildFragmentManager(), "colorPicker");
        } else if (view == this.btnDot) {
            ((ColorPickerDialog) ((ColorPickerDialog) new ColorPickerDialog().withColor(this.btnDotColor)).withPresets(-1, InputDeviceCompat.SOURCE_ANY, -16711681, SupportMenu.CATEGORY_MASK, -65281, -16711936, -12303292, -16776961, ViewCompat.MEASURED_STATE_MASK, -3355444).withListener(new OnColorPickedListener<ColorPickerDialog>() {
                public void onColorPicked(ColorPickerDialog colorPickerDialog, int i) {
                    ColorFragment.this.btnDot.setBackgroundColor(i);
                    int unused = ColorFragment.this.btnDotColor = i;
                    GlobalPreferenceManager.setDotColor(i);
                    ColorFragment.this.mainFragment.setDotColor(i);
                }
            })).show(getChildFragmentManager(), "colorPicker");
        }
    }

    public void setupDropdownColorConfig() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item_layout, getResources().getStringArray(R.array.colorConfigList));
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_overlay_item_layout);
        this.dropdownColorConfig.setAdapter((SpinnerAdapter) arrayAdapter);
        this.dropdownColorConfig.setSelection(GlobalPreferenceManager.getColorConfigType());
        setupRingBtnColor(GlobalPreferenceManager.getColorConfigType());
        this.dropdownColorConfig.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                GlobalPreferenceManager.setColorConfigType(i);
                ColorFragment.this.setupRingBtnColor(i);
                ColorFragment.this.mainFragment.setColorConfigType(i);
            }
        });
    }

    public void setupRingBtnColor(int i) {
        if (i == 0) {
            this.btn1.setVisibility(0);
            this.btn2.setVisibility(8);
            this.btn3.setVisibility(8);
            this.btn4.setVisibility(8);
            this.btn1.setText("");
            this.btn2.setText("");
            this.btn3.setText("");
            this.btn4.setText("");
            this.btn1Color = GlobalPreferenceManager.getBtn1SingleColor();
            this.btn1.setBackgroundColor(GlobalPreferenceManager.getBtn1SingleColor());
        } else if (i == 1) {
            this.btn1.setVisibility(0);
            this.btn2.setVisibility(0);
            this.btn3.setVisibility(0);
            this.btn4.setVisibility(0);
            this.btn1.setText("0-25%");
            this.btn2.setText("26-50%");
            this.btn3.setText("51-75%");
            this.btn4.setText("76-100%");
            this.btn1Color = GlobalPreferenceManager.getBtn1SegColor();
            this.btn2Color = GlobalPreferenceManager.getBtn2SegColor();
            this.btn3Color = GlobalPreferenceManager.getBtn3SegColor();
            this.btn4Color = GlobalPreferenceManager.getBtn4SegColor();
            this.btn1.setBackgroundColor(GlobalPreferenceManager.getBtn1SegColor());
            this.btn2.setBackgroundColor(GlobalPreferenceManager.getBtn2SegColor());
            this.btn3.setBackgroundColor(GlobalPreferenceManager.getBtn3SegColor());
            this.btn4.setBackgroundColor(GlobalPreferenceManager.getBtn4SegColor());
        } else if (i == 2) {
            this.btn1.setVisibility(0);
            this.btn2.setVisibility(0);
            this.btn3.setVisibility(8);
            this.btn4.setVisibility(8);
            this.btn1.setText("Start Color");
            this.btn2.setText("Finish Color");
            this.btn3.setText("");
            this.btn4.setText("");
            this.btn1Color = GlobalPreferenceManager.getBtn1GradColor();
            this.btn2Color = GlobalPreferenceManager.getBtn2GradColor();
            this.btn1.setBackgroundColor(GlobalPreferenceManager.getBtn1GradColor());
            this.btn2.setBackgroundColor(GlobalPreferenceManager.getBtn2GradColor());
        }
    }
}
