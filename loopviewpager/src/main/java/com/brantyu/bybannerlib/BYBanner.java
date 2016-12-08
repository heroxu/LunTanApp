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

import com.imbryk.viewPager.LoopViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import java.lang.reflect.Field;

/**
 * Created by brantyu on 16/3/18.
 */
public class BYBanner extends RelativeLayout {
    private LoopViewPager mViewPager;
    private CirclePageIndicator mCirclePageIndicator;

    private boolean mIsSetAdapter = false;

    public BYBanner(Context context) {
        this(context, null);
    }

    public BYBanner(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.vpiCirclePageIndicatorStyle);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public BYBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
        initViewPagerScroll();
    }

    private void initViews(Context context, AttributeSet attrs) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.banner,this,true);
        mViewPager = (LoopViewPager) rootView.findViewById(R.id.by_loop_viewpager);
        mCirclePageIndicator = (CirclePageIndicator) rootView.findViewById(R.id.by_indicator);
    }

    public void setAutoScroll(boolean flag) {
        if (mIsSetAdapter && mViewPager != null) {
            mViewPager.setAutoScroll(flag);
        }
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
        }

        mIsSetAdapter = true;
    }

    /**
     * 设置ViewPager的滑动速度
     * */
    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(
                    mViewPager.getContext());
            mScroller.set(mViewPager, scroller);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void set(){
        if(mViewPager!=null)
            mViewPager.setBoundaryCaching(true);
    }

    public void showIndicators(boolean flag) {
        if (mCirclePageIndicator != null) {
            if (flag) {
                mCirclePageIndicator.setVisibility(VISIBLE);
            } else {
                mCirclePageIndicator.setVisibility(GONE);
            }
        }
    }


}
