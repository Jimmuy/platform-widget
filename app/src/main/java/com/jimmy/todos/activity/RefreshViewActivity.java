package com.jimmy.todos.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jimmy.todos.R;
import com.jimmy.todos.base.BaseActivity;
import com.jimmy.todos.databinding.RefreshViewBinding;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/7/10     jimmy       v1.0.0        create
 **/

public class RefreshViewActivity extends BaseActivity {

    private RefreshViewBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_refresh_view);
    }
}
