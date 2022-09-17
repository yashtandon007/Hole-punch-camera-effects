package com.holepunchbatteryindicator.holepunchcameraeffects.preferences;

import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.holepunchbatteryindicator.holepunchcameraeffects.util.Constant;
import com.orhanobut.hawk.Hawk;

public class GlobalPreferenceManager {
    public static void setOverlayPermissionGranted(Boolean bool) {
        Hawk.put("OVERLAY_PERMISSION", bool);
    }

    public static boolean isOverlayPermissionGranted() {
        if (Hawk.contains("OVERLAY_PERMISSION")) {
            return ((Boolean) Hawk.get("OVERLAY_PERMISSION")).booleanValue();
        }
        return false;
    }

    public static void setBatteryPtPermissionGranted(Boolean bool) {
        Hawk.put("BATTERY_PERMISSION", bool);
    }

    public static boolean isBatteryPtPermissionGranted() {
        if (Hawk.contains("BATTERY_PERMISSION")) {
            return ((Boolean) Hawk.get("BATTERY_PERMISSION")).booleanValue();
        }
        return false;
    }

    public static void setIntroScreenFinish(boolean z) {
        Hawk.put("INTRO_SCREE_FINISH", Boolean.valueOf(z));
    }

    public static boolean isIntroscreenFinish() {
        if (Hawk.contains("INTRO_SCREE_FINISH")) {
            return ((Boolean) Hawk.get("INTRO_SCREE_FINISH")).booleanValue();
        }
        return false;
    }

    public static void setOverlayServiceStart(boolean z) {
        Hawk.put("OVERLAY_SERVICE", Boolean.valueOf(z));
    }

    public static boolean isOverlayServiceStarted() {
        if (Hawk.contains("OVERLAY_SERVICE")) {
            return ((Boolean) Hawk.get("OVERLAY_SERVICE")).booleanValue();
        }
        return false;
    }

    public static void setShouldDrawDot(boolean z) {
        Hawk.put("DRAW_DOT", Boolean.valueOf(z));
    }

    public static boolean isDotEnabled() {
        if (Hawk.contains("DRAW_DOT")) {
            return ((Boolean) Hawk.get("DRAW_DOT")).booleanValue();
        }
        return false;
    }

    public static void setDotRadius(int i) {
        Hawk.put("DOT_RADIUS", Integer.valueOf(i));
    }

    public static int getDotRadius() {
        if (Hawk.contains("DOT_RADIUS")) {
            return ((Integer) Hawk.get("DOT_RADIUS")).intValue();
        }
        return 4;
    }

    public static void setProgressBarCap(int i) {
        Hawk.put("PROGRESS_CAP", Integer.valueOf(i));
    }

    public static int getProgressBarCap() {
        if (Hawk.contains("PROGRESS_CAP")) {
            return ((Integer) Hawk.get("PROGRESS_CAP")).intValue();
        }
        return 0;
    }

    public static void setOverlayDash(boolean z) {
        Hawk.put("OVERLAY_DASH", Boolean.valueOf(z));
    }

    public static boolean isOverlayDashed() {
        if (Hawk.contains("OVERLAY_DASH")) {
            return ((Boolean) Hawk.get("OVERLAY_DASH")).booleanValue();
        }
        return false;
    }

    public static void setDashLength(float f) {
        Hawk.put("DASH_LENGTH", Float.valueOf(f));
    }

    public static float getDashLength() {
        if (Hawk.contains("DASH_LENGTH")) {
            return ((Float) Hawk.get("DASH_LENGTH")).floatValue();
        }
        return 15.0f;
    }

    public static void setShowAnimation(boolean z) {
        Hawk.put("ANIMATION", Boolean.valueOf(z));
    }

    public static boolean isAnimationEnabled() {
        if (Hawk.contains("ANIMATION")) {
            return ((Boolean) Hawk.get("ANIMATION")).booleanValue();
        }
        return true;
    }

    public static void setChargingAnimationType(int i) {
        Hawk.put("ANIMATION_TYPE", Integer.valueOf(i));
    }

    public static int getChargingAnimationType() {
        if (Hawk.contains("ANIMATION_TYPE")) {
            return ((Integer) Hawk.get("ANIMATION_TYPE")).intValue();
        }
        return 0;
    }

    public static void setColorConfigType(int i) {
        Hawk.put("COLOR_CONFIG", Integer.valueOf(i));
    }

    public static int getColorConfigType() {
        if (Hawk.contains("COLOR_CONFIG")) {
            return ((Integer) Hawk.get("COLOR_CONFIG")).intValue();
        }
        return 1;
    }

    public static void setBtn1SingleColor(int i) {
        Hawk.put("BTN1_SINGLE", Integer.valueOf(i));
    }

    public static int getBtn1SingleColor() {
        if (Hawk.contains("BTN1_SINGLE")) {
            return ((Integer) Hawk.get("BTN1_SINGLE")).intValue();
        }
        return -16776961;
    }

    public static void setBtn1SegColor(int i) {
        Hawk.put("BTN1_SEGMENT", Integer.valueOf(i));
    }

    public static int getBtn1SegColor() {
        return Hawk.contains("BTN1_SEGMENT") ? ((Integer) Hawk.get("BTN1_SEGMENT")).intValue() : SupportMenu.CATEGORY_MASK;
    }

    public static void setBtn2SegColor(int i) {
        Hawk.put("BTN2_SEGMENT", Integer.valueOf(i));
    }

    public static int getBtn2SegColor() {
        return Hawk.contains("BTN2_SEGMENT") ? ((Integer) Hawk.get("BTN2_SEGMENT")).intValue() : InputDeviceCompat.SOURCE_ANY;
    }

    public static void setBtn3SegColor(int i) {
        Hawk.put("BTN3_SEGMENT", Integer.valueOf(i));
    }

    public static int getBtn3SegColor() {
        if (Hawk.contains("BTN3_SEGMENT")) {
            return ((Integer) Hawk.get("BTN3_SEGMENT")).intValue();
        }
        return -16776961;
    }

    public static void setBtn4SegColor(int i) {
        Hawk.put("BTN4_SEGMENT", Integer.valueOf(i));
    }

    public static int getBtn4SegColor() {
        if (Hawk.contains("BTN4_SEGMENT")) {
            return ((Integer) Hawk.get("BTN4_SEGMENT")).intValue();
        }
        return -16711936;
    }

    public static void setBtn1GradColor(int i) {
        Hawk.put("BTN1_GRAD", Integer.valueOf(i));
    }

    public static int getBtn1GradColor() {
        if (Hawk.contains("BTN1_GRAD")) {
            return ((Integer) Hawk.get("BTN1_GRAD")).intValue();
        }
        return -16711681;
    }

    public static void setBtn2GradColor(int i) {
        Hawk.put("BTN2_GRAD", Integer.valueOf(i));
    }

    public static int getBtn2GradColor() {
        if (Hawk.contains("BTN2_GRAD")) {
            return ((Integer) Hawk.get("BTN2_GRAD")).intValue();
        }
        return -16776961;
    }

    public static void setOuterRingColor(int i) {
        Hawk.put("OUTER_RING", Integer.valueOf(i));
    }

    public static int getOuterRingColor() {
        if (Hawk.contains("OUTER_RING")) {
            return ((Integer) Hawk.get("OUTER_RING")).intValue();
        }
        return -7829368;
    }

    public static void setDotColor(int i) {
        Hawk.put("DOT_COLOR", Integer.valueOf(i));
    }

    public static int getDotColor() {
        if (Hawk.contains("DOT_COLOR")) {
            return ((Integer) Hawk.get("DOT_COLOR")).intValue();
        }
        return -16776961;
    }

    public static void setRingRadius(int i) {
        Hawk.put("RING_RADIUS", Integer.valueOf(i));
    }

    public static int getRingRadius() {
        if (Hawk.contains("RING_RADIUS")) {
            return ((Integer) Hawk.get("RING_RADIUS")).intValue();
        }
        return 140;
    }

    public static void setSeekBarStep(int i) {
        Hawk.put("STEPS", Integer.valueOf(i));
    }

    public static int getSeekBarStep() {
        if (Hawk.contains("STEPS")) {
            return ((Integer) Hawk.get("STEPS")).intValue();
        }
        return 5;
    }

    public static void setOutBarThickness(int i) {
        Hawk.put("OUTER_THICKNESS", Integer.valueOf(i));
    }

    public static int getOutBarThickness() {
        if (Hawk.contains("OUTER_THICKNESS")) {
            return ((Integer) Hawk.get("OUTER_THICKNESS")).intValue();
        }
        return 8;
    }

    public static void setInnerBarThickness(int i) {
        Hawk.put("INNER_THICKNESS", Integer.valueOf(i));
    }

    public static int getInnerBarThickness() {
        if (Hawk.contains("INNER_THICKNESS")) {
            return ((Integer) Hawk.get("INNER_THICKNESS")).intValue();
        }
        return 4;
    }

    public static void setStartAngle(int i) {
        Hawk.put("OVERLAY_START_ANGLE", Integer.valueOf(i));
    }

    public static int getStartAngle() {
        return Hawk.contains("OVERLAY_START_ANGLE") ? ((Integer) Hawk.get("OVERLAY_START_ANGLE")).intValue() : Constant.ANGLE_0;
    }

    public static void setProgressDirection(int i) {
        Hawk.put("OVERLAY_PROGRESS_DIRECTION", Integer.valueOf(i));
    }

    public static int getProgressDirection() {
        if (Hawk.contains("OVERLAY_PROGRESS_DIRECTION")) {
            return ((Integer) Hawk.get("OVERLAY_PROGRESS_DIRECTION")).intValue();
        }
        return 0;
    }

    public static void setPositionX(int i) {
        Hawk.put("POSITION_X", Integer.valueOf(i));
    }

    public static int getPositionX() {
        if (Hawk.contains("POSITION_X")) {
            return ((Integer) Hawk.get("POSITION_X")).intValue();
        }
        return 50;
    }

    public static void setPositionY(int i) {
        Hawk.put("POSITION_Y", Integer.valueOf(i));
    }

    public static int getPositionY() {
        if (Hawk.contains("POSITION_Y")) {
            return ((Integer) Hawk.get("POSITION_Y")).intValue();
        }
        return 50;
    }

    public static void setSplashAdCount(int i) {
        Hawk.put("SPLASH_COUNT", Integer.valueOf(i));
    }

    public static int getSplashAdCount() {
        if (Hawk.contains("SPLASH_COUNT")) {
            return ((Integer) Hawk.get("SPLASH_COUNT")).intValue();
        }
        return 0;
    }

    public static void setAppPurchased(boolean z) {
        Hawk.put("APP_PURCHASED", Boolean.valueOf(z));
    }

    public static boolean isAppPurchased() {
        if (Hawk.contains("APP_PURCHASED")) {
            return ((Boolean) Hawk.get("APP_PURCHASED")).booleanValue();
        }
        return false;
    }
}
