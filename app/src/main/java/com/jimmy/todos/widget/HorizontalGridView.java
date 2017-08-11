package com.jimmy.todos.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jimmy.todos.R;
import com.jimmy.todos.databinding.ViewHorizontalGridBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jimmy
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/8/2     jimmy       v1.0.0        create
 **/

public class HorizontalGridView extends LinearLayout {
    private ValueAnimator animatorRight;
    private ViewHorizontalGridBinding binding;
    private ArrayList<CityItem> data;
    private Context context;
    private ValueAnimator animatorLeft;
    private int gridviewWidth;
    private int itemWidth;
    private int animationValue;
    private boolean isRightAnimation;
    private AnimatorSet animatorSet;
    private boolean mCanceled;


    private int mDuration = 30000;

    public HorizontalGridView(Context context) {
        this(context, null);
    }

    public HorizontalGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_horizontal_grid, this, false);
        addView(binding.getRoot());

    }

    public void setData(ArrayList<CityItem> data) {
        this.data = data;
        setGridView();
    }

    public View getGridView() {
        return binding.gvGrid;
    }

    /**
     * 设置GirdView参数，绑定数据
     */
    private void setGridView() {
        final int size = data.size();
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        itemWidth = wm.getDefaultDisplay().getWidth() / 3;
        gridviewWidth = size * itemWidth;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        binding.gvGrid.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        binding.gvGrid.setColumnWidth(itemWidth); // 设置列表项宽
        binding.gvGrid.setStretchMode(GridView.STRETCH_SPACING);
        binding.gvGrid.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(context, data);
        binding.gvGrid.setAdapter(adapter);
        preperAnimation();
        post(new Runnable() {
            @Override
            public void run() {
                animatorSet.start();
            }
        });
    }

    private void preperAnimation() {
        animatorRight = ValueAnimator.ofInt(0, gridviewWidth);
        animatorRight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationValue = (Integer) animation.getAnimatedValue();
                binding.hsvScrollView.smoothScrollTo((Integer) animation.getAnimatedValue(), 0);
            }
        });
        animatorRight.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                animatorRight.setIntValues(animationValue, gridviewWidth);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animatorSet.start();
                    }
                }, mDuration);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorRight.setDuration(mDuration);
        animatorLeft = ValueAnimator.ofInt(gridviewWidth, 0);
        animatorLeft.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationValue = (Integer) animation.getAnimatedValue();
                binding.hsvScrollView.smoothScrollTo((Integer) animation.getAnimatedValue(), 0);
            }
        });
        animatorLeft.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mCanceled = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!mCanceled) {
                    resetAnimation();
                    animatorSet.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCanceled = true;
                animatorLeft.setIntValues(animationValue, 0);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animatorLeft.start();
                    }
                }, mDuration);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorLeft.setDuration(mDuration);
        animatorSet = new AnimatorSet();

        animatorSet.play(animatorLeft).after(animatorRight);
    }

    /**
     * 如果不是手动点击取消的动画，将动画重置
     */
    private void resetAnimation() {
        animatorRight.setIntValues(0, gridviewWidth);
        animatorRight.setDuration(mDuration);
        animatorLeft.setIntValues(gridviewWidth, 0);
        animatorLeft.setDuration(mDuration);
    }

    /**
     * GirdView 数据适配器
     */
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<CityItem> list;

        public GridViewAdapter(Context context, List<CityItem> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_horizontal_grid, null);
            TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
            TextView tvCode = (TextView) convertView.findViewById(R.id.tvCode);
            CityItem city = list.get(position);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelAndStartDelay();
                    Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();

                }
            });
            tvCity.setText(city.getCityName());
            tvCode.setText(city.getCityCode());
            return convertView;
        }
    }


    public static class CityItem {
        private String cityName;
        private String cityCode;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public void cancelAndStartDelay() {
        animatorSet.cancel();
        animatorLeft.cancel();
        animatorRight.cancel();
    }
}
