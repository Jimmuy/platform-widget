package com.jimmy.todos.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jimmy.todos.R;
import com.jimmy.todos.databinding.HeaderSearchBinding;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/7/28     jimmy       v1.0.0        create
 **/

public class HeaderSearchActivity extends Activity implements View.OnClickListener {

    private HeaderSearchBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_header_search);
        binding.btnStart.setOnClickListener(this);
        binding.btnRevers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                binding.tvHeaderSearchView.startEnlargeAnimation();
                break;
            case R.id.btn_revers:
                binding.tvHeaderSearchView.startShrinkAnimation();
                break;
        }
    }
}
