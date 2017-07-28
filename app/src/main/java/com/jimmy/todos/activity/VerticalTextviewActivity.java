package com.jimmy.todos.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jimmy.todos.R;
import com.jimmy.todos.databinding.VerticalTextBinding;

import java.util.ArrayList;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/7/27     jimmy       v1.0.0        create
 **/

public class VerticalTextviewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VerticalTextBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_vertical_text);
        ArrayList texts = new ArrayList();
        texts.add("hahahahaahahah");
        texts.add("heiheiheiheihei");
        texts.add("houhouhouhouhou");
        texts.add("yingyingyingying");
        binding.vrvView.setLeftImage(getResources().getDrawable(R.drawable.icon));
        binding.vrvView.setTextList(texts);
        binding.vrvView.startScroll();
    }
}
