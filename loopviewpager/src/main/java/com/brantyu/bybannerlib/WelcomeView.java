package com.brantyu.bybannerlib;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by xianguo on 19/4/16.
 * 引导页
 */
public class WelcomeView extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private OnSkipListener mOnSkipListener;
    private ViewPager mViewPager;
    private CirclePageIndicator mCirclePageIndicator;

    private ViewPager.OnPageChangeListener listener;

    public WelcomeView(Context context) {
        this(context, null);
    }

    public WelcomeView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.vpiCirclePageIndicatorStyle);
    }

    public void setOnSkipListener(OnSkipListener onSkipListener) {
        mOnSkipListener = onSkipListener;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public WelcomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.welcome, this, true);
        mViewPager = (ViewPager) rootView.findViewById(R.id.by_viewpager);
        mCirclePageIndicator = (CirclePageIndicator) rootView.findViewById(R.id.by_indicator);
        TextView skip = (TextView) rootView.findViewById(R.id.skip_btn);
        skip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSkipListener != null) {
                    mOnSkipListener.skip();
                }
            }
        });
    }

    public void setAdapter(PagerAdapter adapter) {
        setAdapter(adapter, 0);
    }

    public void setAdapter(PagerAdapter adapter, int initialPosition) {
        if (mViewPager != null) {
            mViewPager.setAdapter(adapter);
            if (mCirclePageIndicator != null) {
                mCirclePageIndicator.setViewPager(mViewPager, initialPosition);
            }
            if (listener == null)
                mViewPager.addOnPageChangeListener(this);
            else
                mViewPager.addOnPageChangeListener(listener);
        }
    }

    public void setPageChangeListener(ViewPager.OnPageChangeListener listener) {
        if (listener != null) {
            this.listener = listener;
        } else {
            this.listener = this;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 3)
            mCirclePageIndicator.setVisibility(GONE);
        else
            mCirclePageIndicator.setVisibility(VISIBLE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnSkipListener {
        void skip();
    }
}
