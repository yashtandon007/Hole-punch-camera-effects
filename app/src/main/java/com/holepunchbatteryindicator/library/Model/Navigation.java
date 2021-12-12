package com.holepunchbatteryindicator.library.Model;

import android.util.SparseIntArray;
import java.util.List;

public class Navigation {
    public static final int THEME_DARK = 0;
    public static final int THEME_LIGHT = 1;
    public int colorSelected = 0;
    public SparseIntArray countItem;
    public List<Integer> headerItem;
    public List<Integer> hideItem;
    public List<Integer> iconItem;
    public List<String> nameItem;
    public boolean removeSelector = false;
}
