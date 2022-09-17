package com.holepunchbatteryindicator.library.adapter;

import android.graphics.drawable.Drawable;

public class NavigationArinItemAdapter {
    public boolean checked = false;
    public int colorSelected = 0;
    public int counter;
    public Drawable dIcon;
    public int icon;
    public boolean isHeader;
    public boolean isVisible = true;
    public boolean removeSelector = false;
    public String title;

    public NavigationArinItemAdapter(String str, int i, boolean z, int i2, int i3, boolean z2, boolean z3) {
        this.title = str;
        this.icon = i;
        this.isHeader = z;
        this.counter = i2;
        this.isVisible = z3;
        this.colorSelected = i3;
        this.removeSelector = z2;
    }

    public NavigationArinItemAdapter(String str, Drawable drawable, boolean z, int i, int i2, boolean z2, boolean z3) {
        this.title = str;
        this.dIcon = drawable;
        this.isHeader = z;
        this.counter = i;
        this.isVisible = z3;
        this.colorSelected = i2;
        this.removeSelector = z2;
    }

    public NavigationArinItemAdapter(String str, int i, boolean z, int i2) {
        this.title = str;
        this.icon = i;
        this.isHeader = z;
        this.counter = i2;
        this.isVisible = true;
    }
}
