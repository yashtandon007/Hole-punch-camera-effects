package com.holepunchbatteryindicator.holepunchcameraeffects.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SpinnerAdapter;
import androidx.fragment.app.Fragment;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;
import com.holepunchbatteryindicator.holepunchcameraeffects.util.Constant;
import com.holepunchbatteryindicator.holepunchcameraeffects.view.RemoteButtonLayout;
import com.holepunchbatteryindicator.holepunchcameraeffects.view.RepeatingImageButton;

import fr.ganfra.materialspinner.MaterialSpinner;


public class PositionFragment extends Fragment {
    RemoteButtonLayout dpad;
    private MaterialSpinner dropdownDirection;
    private MaterialSpinner dropdownStartPosition;
    private SeekBar innerBarThickness;
    MainFragment mainFragment;
    private SeekBar outBarThickness;
    private SeekBar seekBarStep;
    private SeekBar seekbarRingRadius;
    int stepX = 2;
    int stepY = 5;

    public static PositionFragment newInstance() {
        return new PositionFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(R.layout.fragment_position, viewGroup, false);
        this.dpad = (RemoteButtonLayout) inflate.findViewById(R.id.dpad);
        this.dropdownDirection = (MaterialSpinner) inflate.findViewById(R.id.dropdownDirection);
        this.dropdownStartPosition = (MaterialSpinner) inflate.findViewById(R.id.dropdownStartPosition);
        setupDropdownDirection();
        setupDropdownStartPosition();
        setUpDPAD();
        this.mainFragment = (MainFragment) getParentFragment();
        this.stepX = GlobalPreferenceManager.getSeekBarStep();
        this.stepY = GlobalPreferenceManager.getSeekBarStep();
        SeekBar seekBar = (SeekBar) inflate.findViewById(R.id.seekBarStep);
        this.seekBarStep = seekBar;
        seekBar.setProgress(GlobalPreferenceManager.getSeekBarStep());
        this.seekBarStep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PositionFragment.this.stepX = i;
                PositionFragment.this.stepY = i;
                GlobalPreferenceManager.setSeekBarStep(i);
            }
        });
        SeekBar seekBar2 = (SeekBar) inflate.findViewById(R.id.outBarThickness);
        this.outBarThickness = seekBar2;
        seekBar2.setProgress(GlobalPreferenceManager.getOutBarThickness());
        this.outBarThickness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PositionFragment.this.mainFragment.setProgressBackgroundStrokeWidthDp(i);
                GlobalPreferenceManager.setOutBarThickness(i);
            }
        });
        SeekBar seekBar3 = (SeekBar) inflate.findViewById(R.id.innerBarThickness);
        this.innerBarThickness = seekBar3;
        seekBar3.setProgress(GlobalPreferenceManager.getInnerBarThickness());
        this.innerBarThickness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PositionFragment.this.mainFragment.setProgressStrokeWidthDp(i);
                GlobalPreferenceManager.setInnerBarThickness(i);
            }
        });
        SeekBar seekBar4 = (SeekBar) inflate.findViewById(R.id.seekbarRingRadius);
        this.seekbarRingRadius = seekBar4;
        seekBar4.setProgress(GlobalPreferenceManager.getRingRadius());
        this.seekbarRingRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PositionFragment.this.mainFragment.setRingRadius(i);
                GlobalPreferenceManager.setRingRadius(i);
            }
        });
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.bannerLayout);
        if (!GlobalPreferenceManager.isAppPurchased()) {
            linearLayout.setVisibility(0);
//            AdView adView = new AdView(getActivity());
//            adView.setAdSize(AdSize.BANNER);
//            adView.setAdUnitId("ca-app-pub-5326282618616655/2200822666");
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
//            layoutParams.gravity = 17;
//            linearLayout.addView(adView, layoutParams);
//            adView.loadAd(new AdRequest.Builder().build());
        } else {
            linearLayout.setVisibility(8);
        }
        return inflate;
    }

    public void setUpDPAD() {
        this.dpad.setLeftButtonListener(new View.OnClickListener() {
            public void onClick(View view) {
                PositionFragment.this.changePositionLeftOrRight(true);
            }
        });
        this.dpad.setRightButtonListener(new View.OnClickListener() {
            public void onClick(View view) {
                PositionFragment.this.changePositionLeftOrRight(false);
            }
        });
        this.dpad.setUpButtonListener(new View.OnClickListener() {
            public void onClick(View view) {
                PositionFragment.this.changePositionUporDown(true);
            }
        });
        this.dpad.setDownButtonListener(new View.OnClickListener() {
            public void onClick(View view) {
                PositionFragment.this.changePositionUporDown(false);
            }
        });
        this.dpad.setUpRepeatListener(new RepeatingImageButton.RepeatListener() {
            public void onRepeat(View view, long j, int i) {
                PositionFragment.this.changePositionUporDown(true);
            }
        }, 100);
        this.dpad.setDownRepeatListener(new RepeatingImageButton.RepeatListener() {
            public void onRepeat(View view, long j, int i) {
                PositionFragment.this.changePositionUporDown(false);
            }
        }, 100);
        this.dpad.setLeftRepeatListener(new RepeatingImageButton.RepeatListener() {
            public void onRepeat(View view, long j, int i) {
                PositionFragment.this.changePositionLeftOrRight(true);
            }
        }, 100);
        this.dpad.setRightRepeatListener(new RepeatingImageButton.RepeatListener() {
            public void onRepeat(View view, long j, int i) {
                PositionFragment.this.changePositionLeftOrRight(false);
            }
        }, 100);
    }

    public void changePositionUporDown(boolean z) {
        int i;
        int overlayPositionX = this.mainFragment.getOverlayPositionX();
        int overlayPositionY = this.mainFragment.getOverlayPositionY();
        if (z) {
            i = overlayPositionY - this.stepY;
            this.mainFragment.setOverlayPosition(overlayPositionX, i);
        } else {
            i = overlayPositionY + this.stepY;
            this.mainFragment.setOverlayPosition(overlayPositionX, i);
        }
        GlobalPreferenceManager.setPositionY(i);
    }

    public void changePositionLeftOrRight(boolean z) {
        int i;
        int overlayPositionX = this.mainFragment.getOverlayPositionX();
        int overlayPositionY = this.mainFragment.getOverlayPositionY();
        if (z) {
            i = overlayPositionX + this.stepX;
            this.mainFragment.setOverlayPosition(i, overlayPositionY);
        } else {
            i = overlayPositionX - this.stepX;
            this.mainFragment.setOverlayPosition(i, overlayPositionY);
        }
        GlobalPreferenceManager.setPositionX(i);
    }

    public void setupDropdownDirection() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item_layout, getResources().getStringArray(R.array.directionList));
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_overlay_item_layout);
        this.dropdownDirection.setAdapter((SpinnerAdapter) arrayAdapter);
        this.dropdownDirection.setSelection(GlobalPreferenceManager.getProgressDirection());
        this.dropdownDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    PositionFragment.this.mainFragment.setProgressDirection(0);
                    GlobalPreferenceManager.setProgressDirection(0);
                } else if (i == 1) {
                    PositionFragment.this.mainFragment.setProgressDirection(1);
                    GlobalPreferenceManager.setProgressDirection(1);
                }
            }
        });
    }

    public void setupDropdownStartPosition() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item_layout, getResources().getStringArray(R.array.positionList));
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_overlay_item_layout);
        this.dropdownStartPosition.setAdapter((SpinnerAdapter) arrayAdapter);
        if (GlobalPreferenceManager.getStartAngle() == 270) {
            this.dropdownStartPosition.setSelection(0);
        } else if (GlobalPreferenceManager.getStartAngle() == 0) {
            this.dropdownStartPosition.setSelection(1);
        } else if (GlobalPreferenceManager.getStartAngle() == 90) {
            this.dropdownStartPosition.setSelection(2);
        } else if (GlobalPreferenceManager.getStartAngle() == 180) {
            this.dropdownStartPosition.setSelection(3);
        }
        this.dropdownStartPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    PositionFragment.this.mainFragment.setStartAngle(Constant.ANGLE_0);
                    GlobalPreferenceManager.setStartAngle(Constant.ANGLE_0);
                } else if (i == 1) {
                    PositionFragment.this.mainFragment.setStartAngle(0);
                    GlobalPreferenceManager.setStartAngle(0);
                } else if (i == 2) {
                    PositionFragment.this.mainFragment.setStartAngle(90);
                    GlobalPreferenceManager.setStartAngle(90);
                } else if (i == 3) {
                    PositionFragment.this.mainFragment.setStartAngle(Constant.ANGLE_270);
                    GlobalPreferenceManager.setStartAngle(Constant.ANGLE_270);
                }
            }
        });
    }
}
