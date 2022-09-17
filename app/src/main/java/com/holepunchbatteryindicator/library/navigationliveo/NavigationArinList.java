package com.holepunchbatteryindicator.library.navigationliveo;

import android.content.Context;

import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.library.Model.HelpItem;
import com.holepunchbatteryindicator.library.Model.Navigation;
import com.holepunchbatteryindicator.library.adapter.NavigationArinItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class NavigationArinList {
    public static List<NavigationArinItemAdapter> getNavigationAdapter(Context context, Navigation navigation) {
        ArrayList arrayList = new ArrayList();
        if (navigation.nameItem == null || navigation.nameItem.size() == 0) {
            throw new RuntimeException(context.getString(R.string.list_null_or_empty));
        }
        int i = 0;
        while (i < navigation.nameItem.size()) {
            String str = navigation.nameItem.get(i);
            int intValue = navigation.iconItem != null ? navigation.iconItem.get(i).intValue() : 0;
            boolean z = navigation.headerItem != null && navigation.headerItem.contains(Integer.valueOf(i));
            int i2 = navigation.countItem != null ? navigation.countItem.get(i, -1) : -1;
            boolean contains = navigation.hideItem != null ? navigation.hideItem.contains(Integer.valueOf(i)) : false;
            if (!z || intValue <= 0) {
                String str2 = "";
                if (z) {
                    if (str == null) {
                        str = str2;
                    }
                    if (str.trim().equals(str2)) {
                        arrayList.add(new NavigationArinItemAdapter(str2, intValue, z, i2, navigation.colorSelected, navigation.removeSelector, !contains));
                        i++;
                    }
                } else if (str == null) {
                    throw new RuntimeException(context.getString(R.string.enter_item_name_position) + i);
                } else if (str.trim().equals(str2)) {
                    throw new RuntimeException(context.getString(R.string.enter_item_name_position) + i);
                }
                str2 = str;
                arrayList.add(new NavigationArinItemAdapter(str2, intValue, z, i2, navigation.colorSelected, navigation.removeSelector, !contains));
                i++;
            } else {
                throw new RuntimeException(context.getString(R.string.value_icon_should_be_0));
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static List<NavigationArinItemAdapter> getNavigationAdapter(Context r18, List<HelpItem> r19, int r20, boolean r21) {
        return null;
    }
}
