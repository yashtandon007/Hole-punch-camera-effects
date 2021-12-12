package com.holepunchbatteryindicator.holepunchcameraeffects.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class RepeatingImageButton extends Button {
    /* access modifiers changed from: private */
    public long mInterval;
    private RepeatListener mListener;
    private int mRepeatCount;
    private Runnable mRepeater;
    private long mStartTime;

    public interface RepeatListener {
        void onRepeat(View view, long j, int i);
    }

    public RepeatingImageButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public RepeatingImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842824);
    }

    public RepeatingImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mInterval = 500;
        this.mRepeater = new Runnable() {
            public void run() {
                RepeatingImageButton.this.doRepeat(false);
                if (RepeatingImageButton.this.isPressed()) {
                    RepeatingImageButton repeatingImageButton = RepeatingImageButton.this;
                    repeatingImageButton.postDelayed(this, repeatingImageButton.mInterval);
                }
            }
        };
        setFocusable(true);
        setLongClickable(true);
    }

    public void setRepeatListener(RepeatListener repeatListener, long j) {
        this.mListener = repeatListener;
        this.mInterval = j;
    }

    public boolean performLongClick() {
        this.mStartTime = SystemClock.elapsedRealtime();
        this.mRepeatCount = 0;
        post(this.mRepeater);
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            removeCallbacks(this.mRepeater);
            if (this.mStartTime != 0) {
                doRepeat(true);
                this.mStartTime = 0;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 23 && i != 66) {
            return super.onKeyDown(i, keyEvent);
        }
        super.onKeyDown(i, keyEvent);
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 23 || i == 66) {
            removeCallbacks(this.mRepeater);
            if (this.mStartTime != 0) {
                doRepeat(true);
                this.mStartTime = 0;
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    /* access modifiers changed from: private */
    public void doRepeat(boolean z) {
        int i;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        RepeatListener repeatListener = this.mListener;
        if (repeatListener != null) {
            long j = elapsedRealtime - this.mStartTime;
            if (z) {
                i = -1;
            } else {
                i = this.mRepeatCount;
                this.mRepeatCount = i + 1;
            }
            repeatListener.onRepeat(this, j, i);
        }
    }
}
