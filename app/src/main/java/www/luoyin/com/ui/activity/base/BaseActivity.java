package www.luoyin.com.ui.activity.base;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.luoyin.com.R;
import www.luoyin.com.utils.swipeback.SwipeBackActivity;
import www.luoyin.com.utils.swipeback.SwipeBackLayout;

/**
 * @author xuxiarong.
 * @time 2016/11/14 20:34.
 * @email 15889318212@163.com
 * @description
 */

public abstract class BaseActivity extends SwipeBackActivity {


    ContextWrapper
    protected SwipeBackLayout mSwipeBackLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.base_fl)
    FrameLayout mBaseFl;
    @BindView(R.id.tv_load_error)
    TextView mTvLoadError;
    @BindView(R.id.pb_loading)
    ContentLoadingProgressBar mPbLoading;
//    @BindView(R.id.cl_layout)
//    CoordinatorLayout mClLayout;
    @BindView(R.id.tv_load_empty)
    TextView mTvLoadEmpty;
    //    //    @BindView(R.id.tv_load_empty)
    //    TextView mTvLoadEmpty;
    //    //    @BindView(R.id.tv_load_error)
    //    TextView mTvLoadError;
    //    //    @BindView(R.id.pb_loading)
    //    ContentLoadingProgressBar mPbLoading;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            scrollToFinishActivity();
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mSwipeBackLayout = getSwipeBackLayout();
        if (isSwipeClose()) {
            mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        } else {
            mSwipeBackLayout.setEnableGesture(false);
        }
        ButterKnife.bind(this);
        mBaseFl.addView(View.inflate(this, getLayoutId(), null));
        afterCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (BasePresenter != null && !subscription.isUnsubscribed()) {
//            subscription.unsubscribe();
//        }
    }


    public void isEmpty(boolean isEmpty) {
         mTvLoadEmpty.setVisibility(isEmpty ?View.VISIBLE:View.GONE);
        mTvLoadEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void isLoading(boolean isLoading) {

        mPbLoading.setVisibility(isLoading ?View.VISIBLE:View.GONE);
    }

    public void isError(boolean isError,String reson) {
        mTvLoadError.setVisibility(isError ?View.VISIBLE:View.GONE);
        Toast.makeText(this, reson, Toast.LENGTH_SHORT).show();
    }


    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

    protected abstract boolean isSwipeClose();
}
