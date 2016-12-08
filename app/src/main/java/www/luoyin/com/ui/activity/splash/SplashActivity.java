package www.luoyin.com.ui.activity.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.brantyu.bybannerlib.WelcomeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.luoyin.com.MainActivity;
import www.luoyin.com.R;

/**
 * @author xuxiarong.
 * @time 2016/11/29 21:14.
 * @email 15889318212@163.com
 * @description
 */

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.splash_wv)
    WelcomeView mSplashWv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ButterKnife.bind(this);
        mSplashWv.setOnSkipListener(new WelcomeView.OnSkipListener() {
            @Override
            public void skip() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
//        mSplashWv.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return new Fragment();
//            }
//
//            @Override
//            public int getCount() {
//                return 4;
//            }
//        });
        mSplashWv.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = (ImageView) View.inflate(SplashActivity.this,R.layout.splash_item,null);
                if(position == 0){
                    iv.setBackgroundColor(Color.RED);
                }else if(position == 1){
                    iv.setBackgroundColor(Color.GREEN);
                }else{
                    iv.setBackgroundColor(Color.YELLOW);
                }
                container.addView(iv);
                return container;
            }
        });
    }
}
