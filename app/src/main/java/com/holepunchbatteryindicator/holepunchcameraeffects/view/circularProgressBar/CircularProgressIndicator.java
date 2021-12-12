package com.holepunchbatteryindicator.holepunchcameraeffects.view.circularProgressBar;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.holepunchbatteryindicator.holepunchcameraeffects.R;
import com.holepunchbatteryindicator.holepunchcameraeffects.preferences.GlobalPreferenceManager;
import com.holepunchbatteryindicator.holepunchcameraeffects.util.Constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularProgressIndicator extends View {
    private static final int ANGLE_END_PROGRESS_BACKGROUND = 360;
    private static final int ANGLE_START_PROGRESS_BACKGROUND = 0;
    public static final int CAP_BUTT = 1;
    public static final int CAP_ROUND = 0;
    private static final int DEFAULT_ANIMATION_DURATION = 1000;
    private static final String DEFAULT_PROGRESS_BACKGROUND_COLOR = "#e0e0e0";
    private static final String DEFAULT_PROGRESS_COLOR = "#3F51B5";
    private static final int DEFAULT_PROGRESS_START_ANGLE = 270;
    private static final int DEFAULT_STROKE_WIDTH_DP = 8;
    private static final int DEFAULT_TEXT_SIZE_SP = 24;
    private static final int DESIRED_WIDTH_DP = 150;
    public static final int DIRECTION_CLOCKWISE = 0;
    public static final int DIRECTION_COUNTERCLOCKWISE = 1;
    public static final int LINEAR_GRADIENT = 1;
    public static final int NO_GRADIENT = 0;
    private static final String PROPERTY_ANGLE = "angle";
    public static final int RADIAL_GRADIENT = 2;
    public static final int SWEEP_GRADIENT = 3;
    private Interpolator animationInterpolator = new AccelerateDecelerateInterpolator();
    private ValueAnimator chargingAnimator;
    private RectF circleBounds;
    private int direction = 0;
    private Paint dotPaint;
    private boolean isAnimationEnabled;
    private boolean isFillBackgroundEnabled;
    private double maxProgressValue = 100.0d;
    private OnProgressChangeListener onProgressChangeListener;
    /* access modifiers changed from: private */
    public ValueAnimator progressAnimator;
    private Paint progressBackgroundPaint;
    private Paint progressPaint;
    private String progressText;
    private ProgressTextAdapter progressTextAdapter;
    private double progressValue = 0.0d;
    private float radius;
    private boolean shouldDrawDot;
    private boolean showChargingAnimation = true;
    private int startAngle = 270;
    /* access modifiers changed from: private */
    public int sweepAngle = 0;
    private Paint textPaint;
    private float textX;
    private float textY;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Cap {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GradientType {
    }

    public interface OnProgressChangeListener {
        void onProgressChanged(double d, double d2);
    }

    public interface ProgressTextAdapter {
        String formatText(double d);
    }

    public CircularProgressIndicator(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        int i;
        Paint.Cap cap;
        int i2;
        int i3;
        int i4;
        int parseColor = Color.parseColor(DEFAULT_PROGRESS_COLOR);
        int parseColor2 = Color.parseColor(DEFAULT_PROGRESS_BACKGROUND_COLOR);
        int dp2px = dp2px(8.0f);
        int sp2px = sp2px(24.0f);
        this.shouldDrawDot = true;
        Paint.Cap cap2 = Paint.Cap.ROUND;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularProgressIndicator);
            parseColor = obtainStyledAttributes.getColor(12, parseColor);
            parseColor2 = obtainStyledAttributes.getColor(9, parseColor2);
            dp2px = obtainStyledAttributes.getDimensionPixelSize(13, dp2px);
            i = obtainStyledAttributes.getDimensionPixelSize(10, dp2px);
            i4 = obtainStyledAttributes.getColor(15, parseColor);
            sp2px = obtainStyledAttributes.getDimensionPixelSize(16, sp2px);
            this.shouldDrawDot = obtainStyledAttributes.getBoolean(3, true);
            i3 = obtainStyledAttributes.getColor(1, parseColor);
            i2 = obtainStyledAttributes.getDimensionPixelSize(2, dp2px);
            int i5 = obtainStyledAttributes.getInt(14, 270);
            this.startAngle = i5;
            if (i5 < 0 || i5 > ANGLE_END_PROGRESS_BACKGROUND) {
                this.startAngle = 270;
            }
            this.isAnimationEnabled = obtainStyledAttributes.getBoolean(4, true);
            this.isFillBackgroundEnabled = obtainStyledAttributes.getBoolean(5, false);
            this.direction = obtainStyledAttributes.getInt(0, 1);
            cap = obtainStyledAttributes.getInt(11, 0) == 0 ? Paint.Cap.ROUND : Paint.Cap.BUTT;
            String string = obtainStyledAttributes.getString(6);
            if (string != null) {
                this.progressTextAdapter = new PatternProgressTextAdapter(string);
            } else {
                this.progressTextAdapter = new DefaultProgressTextAdapter();
            }
            reformatProgressText();
            final int color = obtainStyledAttributes.getColor(8, 0);
            if (color != 0) {
                final int color2 = obtainStyledAttributes.getColor(7, -1);
                if (color2 != -1) {
                    post(new Runnable() {
                        public void run() {
                            CircularProgressIndicator.this.setGradient(color, color2);
                        }
                    });
                } else {
                    throw new IllegalArgumentException("did you forget to specify gradientColorEnd?");
                }
            }
            obtainStyledAttributes.recycle();
        } else {
            i3 = parseColor;
            i = dp2px;
            i2 = i;
            cap = cap2;
            i4 = i3;
        }
        Paint paint = new Paint();
        this.progressPaint = paint;
        paint.setStrokeCap(cap);
        this.progressPaint.setStrokeWidth((float) dp2px);
        this.progressPaint.setStyle(Paint.Style.STROKE);
        this.progressPaint.setColor(parseColor);
        this.progressPaint.setAntiAlias(true);
        Paint.Style style = this.isFillBackgroundEnabled ? Paint.Style.FILL_AND_STROKE : Paint.Style.STROKE;
        Paint paint2 = new Paint();
        this.progressBackgroundPaint = paint2;
        paint2.setStyle(style);
        this.progressBackgroundPaint.setStrokeWidth((float) i);
        this.progressBackgroundPaint.setColor(parseColor2);
        this.progressBackgroundPaint.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.dotPaint = paint3;
        paint3.setStrokeCap(Paint.Cap.ROUND);
        this.dotPaint.setStrokeWidth((float) i2);
        this.dotPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.dotPaint.setColor(i3);
        this.dotPaint.setAntiAlias(true);
        TextPaint textPaint2 = new TextPaint();
        this.textPaint = textPaint2;
        textPaint2.setStrokeCap(Paint.Cap.ROUND);
        this.textPaint.setColor(i4);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextSize((float) sp2px);
        this.circleBounds = new RectF();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        Rect rect = new Rect();
        Paint paint = this.textPaint;
        String str = this.progressText;
        paint.getTextBounds(str, 0, str.length(), rect);
        float strokeWidth = this.dotPaint.getStrokeWidth();
        float strokeWidth2 = this.progressPaint.getStrokeWidth();
        float strokeWidth3 = this.progressBackgroundPaint.getStrokeWidth();
        float max = (float) (((int) (this.shouldDrawDot ? Math.max(strokeWidth, Math.max(strokeWidth2, strokeWidth3)) : Math.max(strokeWidth2, strokeWidth3))) + dp2px(150.0f) + Math.max(paddingBottom + paddingTop, paddingLeft + paddingRight));
        int max2 = (int) (max + ((float) Math.max(rect.width(), rect.height())) + (0.1f * max));
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(max2, size);
        } else if (mode != 1073741824) {
            size = max2;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(max2, size2);
        } else if (mode2 != 1073741824) {
            size2 = max2;
        }
        int min = Math.min((size2 - paddingTop) - paddingBottom, (size - paddingLeft) - paddingRight);
        setMeasuredDimension(min, min);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        calculateBounds(i, i2);
        Shader shader = this.progressPaint.getShader();
        if (shader instanceof RadialGradient) {
            RadialGradient radialGradient = (RadialGradient) shader;
        }
    }

    private void calculateBounds(int i, int i2) {
        float f = (float) i;
        this.radius = f / 2.0f;
        float strokeWidth = this.dotPaint.getStrokeWidth();
        float strokeWidth2 = this.progressPaint.getStrokeWidth();
        float strokeWidth3 = this.progressBackgroundPaint.getStrokeWidth();
        float max = (this.shouldDrawDot ? Math.max(strokeWidth, Math.max(strokeWidth2, strokeWidth3)) : Math.max(strokeWidth2, strokeWidth3)) / 2.0f;
        this.circleBounds.left = max;
        this.circleBounds.top = max;
        this.circleBounds.right = f - max;
        this.circleBounds.bottom = ((float) i2) - max;
        this.radius = this.circleBounds.width() / 2.0f;
        calculateTextBounds();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.progressAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawProgressBackground(canvas);
        drawProgress(canvas);
        if (this.shouldDrawDot) {
            drawDot(canvas);
        }
    }

    private void drawProgressBackground(Canvas canvas) {
        canvas.drawArc(this.circleBounds, 0.0f, 360.0f, false, this.progressBackgroundPaint);
    }

    private void drawProgress(Canvas canvas) {
        canvas.drawArc(this.circleBounds, (float) this.startAngle, (float) this.sweepAngle, false, this.progressPaint);
    }

    private void drawDot(Canvas canvas) {
        double radians = Math.toRadians((double) (this.startAngle + this.sweepAngle + Constant.ANGLE_270));
        canvas.drawPoint(this.circleBounds.centerX() - (this.radius * ((float) Math.cos(radians))), this.circleBounds.centerY() - (this.radius * ((float) Math.sin(radians))), this.dotPaint);
    }

    private void drawText(Canvas canvas) {
        canvas.drawText(this.progressText, this.textX, this.textY, this.textPaint);
    }

    public void setMaxProgress(double d) {
        this.maxProgressValue = d;
        if (d < this.progressValue) {
            setCurrentProgress(d);
        }
        invalidate();
    }

    public void setCurrentProgress(double d) {
        if (d > this.maxProgressValue) {
            this.maxProgressValue = d;
        }
        setProgress(d, this.maxProgressValue);
    }

    public void setProgress(double d, double d2) {
        double d3 = this.direction == 1 ? -((d / d2) * 360.0d) : (d / d2) * 360.0d;
        double d4 = this.progressValue;
        this.maxProgressValue = d2;
        double min = Math.min(d, d2);
        this.progressValue = min;
        OnProgressChangeListener onProgressChangeListener2 = this.onProgressChangeListener;
        if (onProgressChangeListener2 != null) {
            onProgressChangeListener2.onProgressChanged(min, this.maxProgressValue);
        }
        stopProgressAnimation();
        if (this.isAnimationEnabled) {
            startProgressAnimation(d4, d3);
            return;
        }
        this.sweepAngle = (int) d3;
        invalidate();
    }

    private void startProgressAnimation(double d, final double d2) {
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt(PROPERTY_ANGLE, new int[]{this.sweepAngle, (int) d2});
        ValueAnimator ofObject = ValueAnimator.ofObject(new TypeEvaluator<Double>() {
            public Double evaluate(float f, Double d, Double d2) {
                return Double.valueOf(d.doubleValue() + ((d2.doubleValue() - d.doubleValue()) * ((double) f)));
            }
        }, new Object[]{Double.valueOf(d), Double.valueOf(this.progressValue)});
        this.progressAnimator = ofObject;
        ofObject.setDuration(1000);
        this.progressAnimator.setValues(new PropertyValuesHolder[]{ofInt});
        this.progressAnimator.setInterpolator(this.animationInterpolator);
        this.progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int unused = CircularProgressIndicator.this.sweepAngle = ((Integer) valueAnimator.getAnimatedValue(CircularProgressIndicator.PROPERTY_ANGLE)).intValue();
                CircularProgressIndicator.this.invalidate();
            }
        });
        this.progressAnimator.addListener(new DefaultAnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                int unused = CircularProgressIndicator.this.sweepAngle = (int) d2;
                CircularProgressIndicator.this.invalidate();
                ValueAnimator unused2 = CircularProgressIndicator.this.progressAnimator = null;
            }
        });
        this.progressAnimator.start();
    }

    private void stopProgressAnimation() {
        ValueAnimator valueAnimator = this.progressAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    public void showChargingAnimation(boolean z) {
        this.showChargingAnimation = z;
        if (!z) {
            stopChargingAnimation();
        }
    }

    public void setChargingAnimation(boolean z) {
        if (!this.showChargingAnimation) {
            stopChargingAnimation();
        } else if (z) {
            startChargingAnimation(80.0d, 360.0d);
        } else {
            stopChargingAnimation();
        }
    }

    /* access modifiers changed from: private */
    public void startChargingAnimation(double d, double d2) {
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt(PROPERTY_ANGLE, new int[]{this.sweepAngle, (int) d2});
        ValueAnimator ofObject = ValueAnimator.ofObject(new TypeEvaluator<Double>() {
            public Double evaluate(float f, Double d, Double d2) {
                return Double.valueOf(d.doubleValue() + ((d2.doubleValue() - d.doubleValue()) * ((double) f)));
            }
        }, new Object[]{Double.valueOf(d), 90});
        this.chargingAnimator = ofObject;
        ofObject.setDuration(1000);
        this.chargingAnimator.setValues(new PropertyValuesHolder[]{ofInt});
        int chargingAnimationType = GlobalPreferenceManager.getChargingAnimationType();
        if (chargingAnimationType == 0) {
            this.chargingAnimator.setInterpolator(new LinearInterpolator());
        } else if (chargingAnimationType == 1) {
            this.chargingAnimator.setInterpolator(new AccelerateInterpolator());
        } else if (chargingAnimationType == 2) {
            this.chargingAnimator.setInterpolator(new DecelerateInterpolator());
        } else if (chargingAnimationType == 3) {
            this.chargingAnimator.setInterpolator(new BounceInterpolator());
        }
        this.chargingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int unused = CircularProgressIndicator.this.sweepAngle = ((Integer) valueAnimator.getAnimatedValue(CircularProgressIndicator.PROPERTY_ANGLE)).intValue();
                CircularProgressIndicator.this.invalidate();
            }
        });
        this.chargingAnimator.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                int unused = CircularProgressIndicator.this.sweepAngle = 0;
                CircularProgressIndicator.this.startChargingAnimation(80.0d, 360.0d);
            }
        });
        this.chargingAnimator.start();
    }

    private void stopChargingAnimation() {
        ValueAnimator valueAnimator = this.chargingAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.chargingAnimator.removeAllListeners();
        }
    }

    private void reformatProgressText() {
        this.progressText = this.progressTextAdapter.formatText(this.progressValue);
    }

    private Rect calculateTextBounds() {
        Rect rect = new Rect();
        Paint paint = this.textPaint;
        String str = this.progressText;
        paint.getTextBounds(str, 0, str.length(), rect);
        this.textX = this.circleBounds.centerX() - (((float) rect.width()) / 2.0f);
        this.textY = this.circleBounds.centerY() + (((float) rect.height()) / 2.0f);
        return rect;
    }

    private int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    private int sp2px(float f) {
        return (int) TypedValue.applyDimension(2, f, getResources().getDisplayMetrics());
    }

    private void invalidateEverything() {
        calculateBounds(getWidth(), getHeight());
        requestLayout();
        invalidate();
    }

    public void setProgressColor(int i) {
        this.progressPaint.setColor(i);
        invalidate();
    }

    public void setProgressBackgroundColor(int i) {
        this.progressBackgroundPaint.setColor(i);
        invalidate();
    }

    public void setProgressStrokeWidthDp(int i) {
        setProgressStrokeWidthPx(dp2px((float) i));
    }

    public void setProgressWithDashed(boolean z, float f) {
        if (z) {
            this.progressPaint.setPathEffect(new DashPathEffect(new float[]{f, f}, 0.0f));
        } else {
            this.progressPaint.setPathEffect((PathEffect) null);
        }
        invalidateEverything();
    }

    public void setProgressStrokeWidthPx(int i) {
        this.progressPaint.setStrokeWidth((float) i);
        invalidateEverything();
    }

    public void setProgressBackgroundStrokeWidthDp(int i) {
        setProgressBackgroundStrokeWidthPx(dp2px((float) i));
    }

    public void setProgressBackgroundStrokeWidthPx(int i) {
        this.progressBackgroundPaint.setStrokeWidth((float) i);
        invalidateEverything();
    }

    public void setTextColor(int i) {
        this.textPaint.setColor(i);
        Rect rect = new Rect();
        Paint paint = this.textPaint;
        String str = this.progressText;
        paint.getTextBounds(str, 0, str.length(), rect);
        invalidate(rect);
    }

    public void setTextSizeSp(int i) {
        setTextSizePx(sp2px((float) i));
    }

    public void setTextSizePx(int i) {
        float measureText = this.textPaint.measureText(this.progressText) / this.textPaint.getTextSize();
        float width = this.circleBounds.width() - (this.shouldDrawDot ? Math.max(this.dotPaint.getStrokeWidth(), this.progressPaint.getStrokeWidth()) : this.progressPaint.getStrokeWidth());
        if (((float) i) * measureText >= width) {
            i = (int) (width / measureText);
        }
        this.textPaint.setTextSize((float) i);
        invalidate(calculateTextBounds());
    }

    public void setShouldDrawDot(boolean z) {
        this.shouldDrawDot = z;
        if (this.dotPaint.getStrokeWidth() > this.progressPaint.getStrokeWidth()) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    public void setDotColor(int i) {
        this.dotPaint.setColor(i);
        invalidate();
    }

    public void setDotWidthDp(int i) {
        setDotWidthPx(dp2px((float) i));
    }

    public void setDotWidthPx(int i) {
        this.dotPaint.setStrokeWidth((float) i);
        invalidateEverything();
    }

    public void setProgressTextAdapter(ProgressTextAdapter progressTextAdapter2) {
        if (progressTextAdapter2 != null) {
            this.progressTextAdapter = progressTextAdapter2;
        } else {
            this.progressTextAdapter = new DefaultProgressTextAdapter();
        }
        reformatProgressText();
        invalidateEverything();
    }

    public ProgressTextAdapter getProgressTextAdapter() {
        return this.progressTextAdapter;
    }

    public int getProgressColor() {
        return this.progressPaint.getColor();
    }

    public int getProgressBackgroundColor() {
        return this.progressBackgroundPaint.getColor();
    }

    public float getProgressStrokeWidth() {
        return this.progressPaint.getStrokeWidth();
    }

    public float getProgressBackgroundStrokeWidth() {
        return this.progressBackgroundPaint.getStrokeWidth();
    }

    public int getTextColor() {
        return this.textPaint.getColor();
    }

    public float getTextSize() {
        return this.textPaint.getTextSize();
    }

    public boolean isDotEnabled() {
        return this.shouldDrawDot;
    }

    public int getDotColor() {
        return this.dotPaint.getColor();
    }

    public float getDotWidth() {
        return this.dotPaint.getStrokeWidth();
    }

    public double getProgress() {
        return this.progressValue;
    }

    public double getMaxProgress() {
        return this.maxProgressValue;
    }

    public int getStartAngle() {
        return this.startAngle;
    }

    public void setStartAngle(int i) {
        this.startAngle = i;
        invalidate();
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int i) {
        this.direction = i;
        invalidate();
    }

    public int getProgressStrokeCap() {
        return this.progressPaint.getStrokeCap() == Paint.Cap.ROUND ? 0 : 1;
    }

    public void setProgressStrokeCap(int i) {
        Paint.Cap cap = i == 0 ? Paint.Cap.ROUND : Paint.Cap.BUTT;
        if (this.progressPaint.getStrokeCap() != cap) {
            this.progressPaint.setStrokeCap(cap);
            invalidate();
        }
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener2) {
        this.onProgressChangeListener = onProgressChangeListener2;
    }

    public OnProgressChangeListener getOnProgressChangeListener() {
        return this.onProgressChangeListener;
    }

    public void setAnimationEnabled(boolean z) {
        this.isAnimationEnabled = z;
        if (!z) {
            stopProgressAnimation();
        }
    }

    public boolean isAnimationEnabled() {
        return this.isAnimationEnabled;
    }

    public void setFillBackgroundEnabled(boolean z) {
        if (z != this.isFillBackgroundEnabled) {
            this.isFillBackgroundEnabled = z;
            this.progressBackgroundPaint.setStyle(z ? Paint.Style.FILL_AND_STROKE : Paint.Style.STROKE);
            invalidate();
        }
    }

    public boolean isFillBackgroundEnabled() {
        return this.isFillBackgroundEnabled;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.animationInterpolator = interpolator;
    }

    public Interpolator getInterpolator() {
        return this.animationInterpolator;
    }

    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.graphics.Shader] */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.graphics.LinearGradient] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.graphics.RadialGradient] */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setGradient(int r12, int r13) {
        //TODO setgradient
    }

    public int getGradientType() {
        Shader shader = this.progressPaint.getShader();
        if (shader instanceof LinearGradient) {
            return 1;
        }
        if (shader instanceof RadialGradient) {
            return 2;
        }
        return shader instanceof SweepGradient ? 3 : 0;
    }
}
