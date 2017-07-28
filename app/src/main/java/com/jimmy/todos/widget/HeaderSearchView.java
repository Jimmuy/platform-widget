package com.jimmy.todos.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
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

public class HeaderSearchView extends LinearLayout {
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
    }

    public void startEnlargeAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(1f);
        animator.setDuration(800);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(binding.etSearch, "scaleX", 0.99f, 1f);
                objectAnimator.setDuration(300);
                objectAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Log.e("TAB", animatedValue + "");
                LayoutParams layoutParams = new LayoutParams((int) ((1f - animatedValue) * 80), LayoutParams.WRAP_CONTENT);
                binding.llLeftBtn.setLayoutParams(layoutParams);
                LayoutParams layoutParams1 = new LayoutParams((int) ((1f - animatedValue) * 160), LayoutParams.WRAP_CONTENT);
                binding.llRightBtns.setLayoutParams(layoutParams1);
            }
        });
        animator.start();
    }

    public void startShrinkAnimation() {

    }
}
