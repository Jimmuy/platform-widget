package com.jimmy.todos.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.jimmy.todos.R;
import com.jimmy.todos.databinding.HeaderSearchViewBinding;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/7/28     jimmy       v1.0.0        create
 **/

public class HeaderSearchView extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    private Context context;
    private HeaderSearchViewBinding binding;

    public HeaderSearchView(Context context) {
        this(context, null);
    }

    public HeaderSearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_header_search, this, false);
        addView(binding.getRoot());
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    int rightWidth;
    int leftWidth;
    float rate;


    public void startEnlargeAnimation() {
        if (leftWidth == 0 || rightWidth == 0) {
            return;
        }
        binding.llLeftBtn.setVisibility(INVISIBLE);
        binding.llRightBtns.setVisibility(INVISIBLE);
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f, 0.2f);
        animator.setDuration(350);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                LayoutParams layoutParams = new LayoutParams((int) ((animatedValue) * leftWidth), LayoutParams.WRAP_CONTENT);
                binding.llLeftBtn.setLayoutParams(layoutParams);
                LayoutParams layoutParams1 = new LayoutParams((int) ((animatedValue) * rightWidth * rate), LayoutParams.WRAP_CONTENT);

                binding.llRightBtns.setLayoutParams(layoutParams1);

            }
        });
        animator.start();
    }

    public void startShrinkAnimation() {
        if (leftWidth == 0 || rightWidth == 0) {
            return;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(0.2f, 1f, 1.2f, 1f);
        animator.setDuration(350);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                LayoutParams layoutParams = new LayoutParams((int) ((animatedValue) * leftWidth), LayoutParams.WRAP_CONTENT);
                binding.llLeftBtn.setLayoutParams(layoutParams);
                LayoutParams layoutParams1 = new LayoutParams((int) ((animatedValue) * rightWidth), LayoutParams.WRAP_CONTENT);
                binding.llRightBtns.setLayoutParams(layoutParams1);
            }
        });
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                binding.llLeftBtn.setVisibility(VISIBLE);
                binding.llRightBtns.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public void onGlobalLayout() {
        leftWidth = binding.llLeftBtn.getWidth();
        rightWidth = binding.llRightBtns.getWidth();
        rate = (float) leftWidth / (float) rightWidth;
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }
}
