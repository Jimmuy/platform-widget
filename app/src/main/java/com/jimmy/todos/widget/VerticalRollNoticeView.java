package com.jimmy.todos.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.jimmy.todos.R;
import com.jimmy.todos.databinding.VerticalRollBinding;

import java.util.ArrayList;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/7/28     jimmy       v1.0.0        create
 **/

public class VerticalRollNoticeView extends LinearLayout {

    private VerticalRollBinding binding;
    private ArrayList<String> texts = new ArrayList<>();

    public VerticalRollNoticeView(Context context) {
        this(context, null);
    }

    public VerticalRollNoticeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalRollNoticeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_vertical_roll_notice, this, false);
        initTextView();
        addView(binding.getRoot());
    }

    private void initTextView() {
        binding.vtvText.setTextList(texts);
        binding.vtvText.setTextStillTime(2000);
        binding.vtvText.setText(26, 0, Color.RED);//设置属性,具体跟踪源码
        binding.vtvText.setAnimTime(300);

    }

    /**
     * 设置需要滚动的文字
     */
    public void setTextList(ArrayList<String> list) {
        this.texts = list;
        binding.vtvText.setTextList(texts);
    }

    /**
     * 开始滚动
     */
    public void startScroll() {
        binding.vtvText.startAutoScroll();
    }

    /**
     * 设置左边的图案
     */
    public void setLeftImage(Drawable drawable) {
        binding.ivLeft.setImageDrawable(drawable);
    }
}
