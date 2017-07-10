package com.jimmy.todos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by yanghui on 15/12/29.
 */
public class QCScrollView extends ScrollView {

    private OnQCScrollViewListener onQCScrollViewListener = null;

    private OnInterceptTouchListener onInterceptTouchListener = null;

    public interface OnQCScrollViewListener {
        void onScrollChanged(QCScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    public interface OnInterceptTouchListener {
        boolean onInterceptTouchEvent(MotionEvent ev);
    }

    private QCScrollBottomListener qcScrollBottomListener = null;

    public interface QCScrollBottomListener {
        void isScrollBottom(boolean bool);
    }


    public QCScrollView(Context context) {
        super(context);
    }

    public QCScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public QCScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnQCScrollViewListener(OnQCScrollViewListener onQCScrollViewListener) {
        this.onQCScrollViewListener = onQCScrollViewListener;
    }

    public void setOnInterceptTouchListener(OnInterceptTouchListener onInterceptTouchListener) {
        this.onInterceptTouchListener = onInterceptTouchListener;
    }

    public void setQcScrollBottomListener(QCScrollBottomListener qcScrollBottomListener) {
        this.qcScrollBottomListener = qcScrollBottomListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onQCScrollViewListener != null) {
            onQCScrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.onInterceptTouchListener != null) {
            if (!onInterceptTouchListener.onInterceptTouchEvent(ev)) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (qcScrollBottomListener != null && scrollY != 0) {
            qcScrollBottomListener.isScrollBottom(clampedY);
        }
    }
}
