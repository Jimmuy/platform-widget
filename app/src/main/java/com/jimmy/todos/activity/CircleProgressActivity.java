package com.jimmy.todos.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jimmy.todos.R;
import com.jimmy.todos.base.BaseActivity;
import com.jimmy.todos.databinding.CircleProgressBinding;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/7/11     jimmy       v1.0.0        create
 **/

public class CircleProgressActivity extends BaseActivity {


    private CircleProgressBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_circle_progress);
        binding.ivCircle.startAnimation();
    }
}
