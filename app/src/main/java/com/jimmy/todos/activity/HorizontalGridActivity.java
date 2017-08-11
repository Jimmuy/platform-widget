package com.jimmy.todos.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jimmy.todos.R;
import com.jimmy.todos.base.BaseActivity;
import com.jimmy.todos.databinding.HorizontalGridActivityBinding;
import com.jimmy.todos.widget.HorizontalGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/8/1     jimmy       v1.0.0        create
 **/

public class HorizontalGridActivity extends BaseActivity {

    List<HorizontalGridView.CityItem> cityList;
    private HorizontalGridActivityBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hroizontal_grid);
        setData();
    }

    /**
     * 设置数据
     */
    private void setData() {
        cityList = new ArrayList<HorizontalGridView.CityItem>();
        HorizontalGridView.CityItem item = new HorizontalGridView.CityItem();
        item.setCityName("深圳");
        item.setCityCode("0755");
        cityList.add(item);
        item = new HorizontalGridView.CityItem();
        item.setCityName("上海");
        item.setCityCode("021");
        cityList.add(item);
        item = new HorizontalGridView.CityItem();
        item.setCityName("广州");
        item.setCityCode("020");
        cityList.add(item);
        item = new HorizontalGridView.CityItem();
        item.setCityName("北京");
        item.setCityCode("010");
        cityList.add(item);
        item = new HorizontalGridView.CityItem();
        item.setCityName("武汉");
        item.setCityCode("027");
        cityList.add(item);
        item = new HorizontalGridView.CityItem();
        item.setCityName("孝感");
        item.setCityCode("0712");
        cityList.add(item);
        cityList.addAll(cityList);
        binding.hgvGrid.setData((ArrayList<HorizontalGridView.CityItem>) cityList);
    }


}
