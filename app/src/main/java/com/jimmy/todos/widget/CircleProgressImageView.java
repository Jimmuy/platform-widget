package com.jimmy.todos.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/7/11     jimmy       v1.0.0        create
 **/

public class CircleProgressImageView extends android.support.v7.widget.AppCompatImageView {
    private Paint mPaintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mRawBitmap;
    private BitmapShader mShader;
    private Matrix mMatrix = new Matrix();
    private float animatedValue = 0;
    private Animator.AnimatorListener listener;
    private ValueAnimator valueAnimator;

    public CircleProgressImageView(Context context) {
        super(context);
    }

    public CircleProgressImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap rawBitmap = getBitmap(getDrawable());
        if (rawBitmap != null) {
            int viewWidth = getWidth();
            int viewHeight = getHeight();
            int viewMinSize = Math.min(viewWidth, viewHeight);


            if (mShader == null || !rawBitmap.equals(mRawBitmap)) {
                mRawBitmap = rawBitmap;
                mShader = new BitmapShader(mRawBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (mShader != null) {
                mMatrix.setScale(viewMinSize / rawBitmap.getWidth(), viewMinSize / rawBitmap.getHeight());
                mShader.setLocalMatrix(mMatrix);
            }
            mPaintBitmap.setShader(mShader);
            float radius = viewMinSize / 2.0f;
            canvas.drawCircle(radius, radius, radius, mPaintBitmap);

            if (animatedValue > 0 && animatedValue < 100) {
                Paint paint = getPaint(Color.parseColor("#FFDEBB"));
                float errorSize = (paint.getStrokeWidth() / 2);
                canvas.drawCircle(radius, radius, radius - errorSize, paint);

                RectF oval = new RectF(0 + errorSize, 0 + errorSize, viewMinSize - errorSize, viewMinSize - errorSize);
                paint.setColor(Color.parseColor("#FD8746"));
                canvas.drawArc(oval, -90, 3.6f * animatedValue, false, paint);
            }
        } else {
            super.onDraw(canvas);
        }
    }

    @NonNull
    private Paint getPaint(int black) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(black);
        paint.setStrokeWidth(5);              //线宽
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int min = Math.min(widthSize, heightSize);
        setMeasuredDimension(min, min);
    }

    public Animator getAnimator() {
        return valueAnimator;
    }

    public void startAnimation() {
        startAnimation(3000, null);
    }

    public void startAnimation(long duration) {
        startAnimation(duration, null);
    }


    public void startAnimation(Animator.AnimatorListener listener) {
        startAnimation(3000, listener);
    }

    public void startAnimation(long duration, Animator.AnimatorListener listener) {
        this.listener = listener;
        valueAnimator = ValueAnimator.ofFloat(0, 100f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(duration).start();
        if (listener != null) {
            valueAnimator.addListener(listener);
        }
    }


    private Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof ColorDrawable) {
            Rect rect = drawable.getBounds();
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;
            int color = ((ColorDrawable) drawable).getColor();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
            return bitmap;
        } else {
            return null;
        }
    }
}
