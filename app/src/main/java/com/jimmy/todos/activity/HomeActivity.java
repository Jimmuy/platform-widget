package com.jimmy.todos.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.jimmy.todos.R;
import com.jimmy.todos.base.BaseActivity;
import com.jimmy.todos.databinding.HomeBinding;

public class HomeActivity extends BaseActivity implements View.OnClickListener{
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
        binding.tvCircleProgress.setOnClickListener(this);
        binding.tvVerticalText.setOnClickListener(this);
        binding.tvHeaderSearch.setOnClickListener(this);
        binding.tvHorizontalGrid.setOnClickListener(this);
        binding.tvJsBridge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_refresh_view:
                intent = new Intent(this, RefreshViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_circle_progress:
                intent = new Intent(this, CircleProgressActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_vertical_text:
                intent = new Intent(this, VerticalTextviewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_header_search:
                intent = new Intent(this, HeaderSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_horizontal_grid:
                intent = new Intent(this, HorizontalGridActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_js_bridge:
                intent = new Intent(this, JsBridgeActivity.class);
                startActivity(intent);
                break;
        }
    }

}
