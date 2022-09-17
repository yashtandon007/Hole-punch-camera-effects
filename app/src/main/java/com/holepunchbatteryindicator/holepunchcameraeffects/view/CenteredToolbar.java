package com.holepunchbatteryindicator.holepunchcameraeffects.view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class CenteredToolbar extends Toolbar {
    private TextView centeredTitleTextView;

    public CenteredToolbar(Context context) {
        super(context);
    }

    public CenteredToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CenteredToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setTitle(int i) {
        setTitle((CharSequence) getResources().getString(i));
    }

    public void setTitle(CharSequence charSequence) {
        getCenteredTitleTextView().setText(charSequence);
    }

    public void setTitleColor(int i) {
        getCenteredTitleTextView().setTextColor(i);
    }

    public CharSequence getTitle() {
        return getCenteredTitleTextView().getText().toString();
    }

    public void setTypeface(Typeface typeface) {
        getCenteredTitleTextView().setTypeface(typeface);
    }

    private TextView getCenteredTitleTextView() {
        if (this.centeredTitleTextView == null) {
            TextView textView = new TextView(getContext());
            this.centeredTitleTextView = textView;
            textView.setSingleLine();
            this.centeredTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
            this.centeredTitleTextView.setGravity(17);
            this.centeredTitleTextView.setTextAppearance(getContext(), 2131886421);
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            this.centeredTitleTextView.setLayoutParams(layoutParams);
            addView(this.centeredTitleTextView);
        }
        return this.centeredTitleTextView;
    }
}
