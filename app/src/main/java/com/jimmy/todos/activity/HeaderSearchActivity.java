package com.jimmy.todos.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

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

public class HeaderSearchActivity extends Activity {

    private HeaderSearchBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_header_search);
        binding.tvHeaderSearchView.startEnlargeAnimation();
    }
}
