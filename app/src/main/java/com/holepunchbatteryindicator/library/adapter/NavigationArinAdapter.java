package com.holepunchbatteryindicator.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

import com.holepunchbatteryindicator.holepunchcameraeffects.R;

import java.util.List;

public class NavigationArinAdapter extends BaseAdapter {
    private int mColorCounter = 0;
    private int mColorDefault = 0;
    private int mColorIcon = 0;
    private int mColorName = 0;
    private int mColorSeparator = 0;
    private int mColorSubHeader = 0;
    private final List<NavigationArinItemAdapter> mList;
    private int mNewSelector = 0;
    private boolean mRemoveAlpha = false;
    private boolean mRemoveColorFilter = false;
    private int mSelectorDefault = 0;
    private final Context mcontext;

    public long getItemId(int i) {
        return (long) i;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public NavigationArinAdapter(Context context, List<NavigationArinItemAdapter> list, List<Boolean> list2, List<Integer> list3) {
        this.mList = list;
        this.mcontext = context;
        this.mNewSelector = list3.get(0).intValue();
        this.mColorDefault = list3.get(1).intValue();
        this.mColorIcon = list3.get(2).intValue();
        this.mColorName = list3.get(3).intValue();
        this.mColorSeparator = list3.get(4).intValue();
        this.mColorCounter = list3.get(5).intValue();
        this.mSelectorDefault = list3.get(6).intValue();
        this.mColorSubHeader = list3.get(7).intValue();
        this.mRemoveAlpha = list2.get(0).booleanValue();
        this.mRemoveColorFilter = list2.get(1).booleanValue();
    }

    public int getCount() {
        return this.mList.size();
    }

    public NavigationArinItemAdapter getItem(int i) {
        return this.mList.get(i);
    }

    public int getItemViewType(int i) {
        return this.mList.get(i).isVisible ^ true ? 1 : 0;
    }

    public boolean isEnabled(int i) {
        return !getItem(i).isHeader;
    }

    public void setChecked(int i, boolean z) {
        //TODO yash
//        this.mList.get(i).checked = z;
//        notifyDataSetChanged();
    }

    public void resetarCheck() {
        //TODO yash
//        for (int i = 0; i < this.mList.size(); i++) {
//            this.mList.get(i).checked = false;
//        }
//        notifyDataSetChanged();
    }

    public void setNewName(int i, String str) {
        this.mList.get(i).title = str;
        notifyDataSetChanged();
    }

    public void setNewIcon(int i, int i2) {
        this.mList.get(i).icon = i2;
        notifyDataSetChanged();
    }

    public void setNewCounterValue(int i, int i2) {
        this.mList.get(i).counter = i2;
        notifyDataSetChanged();
    }

    public void setNewInformationItem(int i, String str, int i2, int i3) {
        this.mList.get(i).title = str;
        this.mList.get(i).icon = i2;
        this.mList.get(i).counter = i3;
        notifyDataSetChanged();
    }

    public void setIncreasingCounterValue(int i, int i2) {
        this.mList.get(i).counter += i2;
        notifyDataSetChanged();
    }

    public void setDecreaseCountervalue(int i, int i2) {
        this.mList.get(i).counter -= i2;
        notifyDataSetChanged();
    }

    public void setVisibleItemNavigation(int i, boolean z) {
        this.mList.get(i).isVisible = z;
        notifyDataSetChanged();
    }

    class ViewHolder {
        public TextView counter;
        public ImageView icon;
        public RelativeLayout layoutDados;
        public LinearLayout layoutSeparator;
        public View separator;
        public TextView title;

        public ViewHolder() {
        }
    }

    private void setAlpha(View view, float f) {
        if (this.mRemoveAlpha) {
            f = 1.0f;
        }
        view.setAlpha(f);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        int i3;
        int i4;
        String str;
        int i5;
        int i6;
        int i7;
        ViewHolder viewHolder = new ViewHolder();
        View inflate = ((LayoutInflater) this.mcontext.getSystemService("layout_inflater")).inflate(R.layout.navigation_list_item, viewGroup, false);
        viewHolder.title = (TextView) inflate.findViewById(R.id.title);
        viewHolder.counter = (TextView) inflate.findViewById(R.id.counter);
        viewHolder.icon = (ImageView) inflate.findViewById(R.id.icon);
        viewHolder.separator = inflate.findViewById(R.id.separator);
        viewHolder.layoutDados = (RelativeLayout) inflate.findViewById(R.id.layoutDados);
        viewHolder.layoutSeparator = (LinearLayout) inflate.findViewById(R.id.layoutSeparator);
        inflate.setTag(viewHolder);
        NavigationArinItemAdapter navigationArinItemAdapter = this.mList.get(i);
        float f = 0.87f;
        float f2 = 1.0f;
        if (viewHolder.title != null) {
            viewHolder.title.setText(navigationArinItemAdapter.title);
            if (!navigationArinItemAdapter.isHeader) {
                viewHolder.layoutSeparator.setVisibility(8);
                viewHolder.layoutDados.setVisibility(getItemViewType(i) == 0 ? 0 : 8);
                setAlpha(viewHolder.title, navigationArinItemAdapter.checked ? 1.0f : 0.87f);
            } else {
                viewHolder.layoutSeparator.setVisibility(0);
                if (this.mColorSeparator > 0) {
                    viewHolder.separator.setBackgroundResource(this.mColorSeparator);
                }
                if (navigationArinItemAdapter.title.equals("")) {
                    viewHolder.title.setVisibility(8);
                    viewHolder.layoutDados.setVisibility(8);
                } else {
                    viewHolder.layoutDados.setVisibility(0);
                }
            }
            TextView textView = viewHolder.title;
            if (navigationArinItemAdapter.isHeader || !navigationArinItemAdapter.checked || navigationArinItemAdapter.colorSelected <= 0) {
                int i8 = this.mColorDefault;
                if (i8 != 0) {
                    i6 = ContextCompat.getColor(this.mcontext, i8);
                } else {
                    int i9 = this.mColorName;
                    if (i9 > 0) {
                        i6 = ContextCompat.getColor(this.mcontext, i9);
                    } else if (!navigationArinItemAdapter.isHeader || (i7 = this.mColorSubHeader) <= 0) {
                        i6 = ContextCompat.getColor(this.mcontext, R.color.nliveo_black);
                    } else {
                        i6 = ContextCompat.getColor(this.mcontext, i7);
                    }
                }
            } else {
                i6 = ContextCompat.getColor(this.mcontext, navigationArinItemAdapter.colorSelected);
            }
            textView.setTextColor(i6);
        }
        if (viewHolder.counter != null) {
            if (navigationArinItemAdapter.counter >= 1) {
                TextView textView2 = viewHolder.counter;
                if (navigationArinItemAdapter.checked) {
                    f = 1.0f;
                }
                setAlpha(textView2, f);
                viewHolder.counter.setVisibility(0);
                TextView textView3 = viewHolder.counter;
                if (navigationArinItemAdapter.counter > 99) {
                    str = "99+";
                } else {
                    str = navigationArinItemAdapter.counter + "";
                }
                textView3.setText(str);
                TextView textView4 = viewHolder.counter;
                if (navigationArinItemAdapter.isHeader || !navigationArinItemAdapter.checked || navigationArinItemAdapter.colorSelected <= 0) {
                    int i10 = this.mColorDefault;
                    if (i10 != 0) {
                        i5 = ContextCompat.getColor(this.mcontext, i10);
                    } else {
                        int i11 = this.mColorCounter;
                        if (i11 > 0) {
                            i5 = ContextCompat.getColor(this.mcontext, i11);
                        } else {
                            i5 = ContextCompat.getColor(this.mcontext, R.color.nliveo_black);
                        }
                    }
                } else {
                    i5 = ContextCompat.getColor(this.mcontext, navigationArinItemAdapter.colorSelected);
                }
                textView4.setTextColor(i5);
            } else {
                viewHolder.counter.setVisibility(8);
                if (navigationArinItemAdapter.counter < 0) {
                    setNewCounterValue(i, 0);
                }
            }
        }
        if (viewHolder.icon != null) {
            if (navigationArinItemAdapter.icon != 0) {
                viewHolder.icon.setVisibility(0);
                viewHolder.icon.setImageResource(navigationArinItemAdapter.icon);
                ImageView imageView = viewHolder.icon;
                if (navigationArinItemAdapter.isHeader || !navigationArinItemAdapter.checked) {
                    f2 = 0.54f;
                }
                setAlpha(imageView, f2);
                if (!this.mRemoveColorFilter) {
                    ImageView imageView2 = viewHolder.icon;
                    if (navigationArinItemAdapter.isHeader || !navigationArinItemAdapter.checked || navigationArinItemAdapter.colorSelected <= 0) {
                        int i12 = this.mColorDefault;
                        if (i12 != 0) {
                            i4 = ContextCompat.getColor(this.mcontext, i12);
                        } else {
                            int i13 = this.mColorIcon;
                            if (i13 > 0) {
                                i4 = ContextCompat.getColor(this.mcontext, i13);
                            } else {
                                i4 = ContextCompat.getColor(this.mcontext, R.color.nliveo_black);
                            }
                        }
                    } else {
                        i4 = ContextCompat.getColor(this.mcontext, navigationArinItemAdapter.colorSelected);
                    }
                    imageView2.setColorFilter(i4);
                }
            } else {
                viewHolder.icon.setVisibility(0);
                viewHolder.icon.setImageDrawable(navigationArinItemAdapter.dIcon);
                ImageView imageView3 = viewHolder.icon;
                if (navigationArinItemAdapter.isHeader || !navigationArinItemAdapter.checked) {
                    f2 = 0.54f;
                }
                setAlpha(imageView3, f2);
                if (!this.mRemoveColorFilter) {
                    ImageView imageView4 = viewHolder.icon;
                    if (navigationArinItemAdapter.isHeader || !navigationArinItemAdapter.checked || navigationArinItemAdapter.colorSelected <= 0) {
                        int i14 = this.mColorDefault;
                        if (i14 != 0) {
                            i3 = ContextCompat.getColor(this.mcontext, i14);
                        } else {
                            int i15 = this.mColorIcon;
                            if (i15 > 0) {
                                i3 = ContextCompat.getColor(this.mcontext, i15);
                            } else {
                                i3 = ContextCompat.getColor(this.mcontext, R.color.nliveo_black);
                            }
                        }
                    } else {
                        i3 = ContextCompat.getColor(this.mcontext, navigationArinItemAdapter.colorSelected);
                    }
                    imageView4.setColorFilter(i3);
                }
            }
        }
        if (navigationArinItemAdapter.isHeader) {
            int i16 = this.mSelectorDefault;
            if (i16 == 0) {
                i16 = R.drawable.selector_no_check_item_navigation;
            }
            inflate.setBackgroundResource(i16);
        } else if (navigationArinItemAdapter.checked) {
            if (!navigationArinItemAdapter.removeSelector) {
                i2 = this.mNewSelector;
                if (i2 == 0) {
                    i2 = R.drawable.selector_check_item_navigation;
                }
            } else {
                i2 = R.drawable.selector_no_item_navigation;
            }
            inflate.setBackgroundResource(i2);
        } else {
            inflate.setBackgroundResource(this.mSelectorDefault == 0 ? R.drawable.selector_no_check_item_navigation : R.drawable.selector_no_item_navigation);
        }
        return inflate;
    }
}
