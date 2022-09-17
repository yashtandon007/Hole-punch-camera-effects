package com.holepunchbatteryindicator.holepunchcameraeffects.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.view.RepeatingImageButton;

public class RemoteButtonLayout extends LinearLayout {
    private RepeatingImageButton mDownButton;
    private RepeatingImageButton mLeftButton;
    private ImageButton mOkButton;
    private RepeatingImageButton mRightButton;
    private RepeatingImageButton mUpButton;

    public RemoteButtonLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public RemoteButtonLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_remote_button, this);
        this.mUpButton = (RepeatingImageButton) findViewById(R.id.up_button);
        this.mLeftButton = (RepeatingImageButton) findViewById(R.id.left_button);
        this.mRightButton = (RepeatingImageButton) findViewById(R.id.right_button);
        this.mDownButton = (RepeatingImageButton) findViewById(R.id.down_button);
        this.mOkButton = (ImageButton) findViewById(R.id.ok_button);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == 1073741824) {
            setMeasuredDimension(size, size2);
            return;
        }
        int min = Math.min(size, size2);
        setMeasuredDimension(min, min);
    }

    public void setUpButtonListener(View.OnClickListener onClickListener) {
        this.mUpButton.setOnClickListener(onClickListener);
    }

    public void setLeftButtonListener(View.OnClickListener onClickListener) {
        this.mLeftButton.setOnClickListener(onClickListener);
    }

    public void setRightButtonListener(View.OnClickListener onClickListener) {
        this.mRightButton.setOnClickListener(onClickListener);
    }

    public void setDownButtonListener(View.OnClickListener onClickListener) {
        this.mDownButton.setOnClickListener(onClickListener);
    }

    public void setOkButtonListener(View.OnClickListener onClickListener) {
        this.mOkButton.setOnClickListener(onClickListener);
    }

    public void setUpRepeatListener(RepeatingImageButton.RepeatListener repeatListener, int i) {
        this.mUpButton.setRepeatListener(repeatListener, (long) i);
    }

    public void setDownRepeatListener(RepeatingImageButton.RepeatListener repeatListener, int i) {
        this.mDownButton.setRepeatListener(repeatListener, (long) i);
    }

    public void setLeftRepeatListener(RepeatingImageButton.RepeatListener repeatListener, int i) {
        this.mLeftButton.setRepeatListener(repeatListener, (long) i);
    }

    public void setRightRepeatListener(RepeatingImageButton.RepeatListener repeatListener, int i) {
        this.mRightButton.setRepeatListener(repeatListener, (long) i);
    }
}
