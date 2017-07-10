package com.jimmy.todos.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.jimmy.todos.R;
import com.jimmy.todos.base.BaseActivity;
import com.jimmy.todos.databinding.HomeBinding;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TODO_LIST = "todo_list";

    private HomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.tvRefreshView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_refresh_view:
                Intent intent = new Intent(this, RefreshViewActivity.class);
                startActivity(intent);
                break;

        }
    }
}
