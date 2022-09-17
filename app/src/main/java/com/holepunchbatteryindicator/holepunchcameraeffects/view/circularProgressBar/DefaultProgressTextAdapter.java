package com.holepunchbatteryindicator.holepunchcameraeffects.view.circularProgressBar;

import com.holepunchbatteryindicator.holepunchcameraeffects.view.circularProgressBar.CircularProgressIndicator;

public final class DefaultProgressTextAdapter implements CircularProgressIndicator.ProgressTextAdapter {
    public String formatText(double d) {
        return String.valueOf((int) d);
    }
}
