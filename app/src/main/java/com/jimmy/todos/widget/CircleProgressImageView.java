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
import android.util.Log;
import android.widget.ImageView;

import static android.R.attr.width;
import static android.R.attr.x;
import static android.R.attr.y;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/7/11     jimmy       v1.0.0        create
 **/

public class CircleProgressImageView extends ImageView {
    private Paint mPaintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mRawBitmap;
    private BitmapShader mShader;
    private Matrix mMatrix = new Matrix();
    private float animatedValue = 0;

    public CircleProgressImageView(Context context) {
        this(context, null);
    }

    public CircleProgressImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
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
            float dstWidth = viewMinSize;
            float dstHeight = viewMinSize;
            if (mShader == null || !rawBitmap.equals(mRawBitmap)) {
                mRawBitmap = rawBitmap;
                mShader = new BitmapShader(mRawBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (mShader != null) {
                mMatrix.setScale(dstWidth / rawBitmap.getWidth(), dstHeight / rawBitmap.getHeight());
                mShader.setLocalMatrix(mMatrix);
            }
            mPaintBitmap.setShader(mShader);
            float radius = viewMinSize / 2.0f;
            canvas.drawCircle(radius, radius, radius - 30f, mPaintBitmap);
            canvas.drawCircle(radius, radius, radius - 30f, getPaint(Color.BLACK));

            RectF oval = new RectF(getLeft() + 30, getTop() + 30, getRight() - 30, getBottom() - 30);

            canvas.drawArc(oval, -90, 3.6f * animatedValue, false, getPaint(Color.RED));

        } else {
            super.onDraw(canvas);
        }
    }

    @NonNull
    private Paint getPaint(int black) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(black);
        paint.setStrokeWidth((float) 30);              //线宽
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

    public void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(10000).start();
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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
