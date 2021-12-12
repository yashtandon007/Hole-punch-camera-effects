package com.holepunchbatteryindicator.holepunchcameraeffects.view.circularProgressBar;

import com.holepunchbatteryindicator.holepunchcameraeffects.view.circularProgressBar.CircularProgressIndicator;

public final class PatternProgressTextAdapter implements CircularProgressIndicator.ProgressTextAdapter {
    private String pattern;

    public PatternProgressTextAdapter(String str) {
        this.pattern = str;
    }

    public String formatText(double d) {
        return String.format(this.pattern, new Object[]{Double.valueOf(d)});
    }
}
