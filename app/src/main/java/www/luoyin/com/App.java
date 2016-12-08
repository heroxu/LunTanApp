package www.luoyin.com;

import android.app.Application;
import android.content.Context;

import www.luoyin.com.utils.AppContextUtil;

/**
 * @author xuxiarong.
 * @time 2016/11/15 19:36.
 * @email 15889318212@163.com
 * @description
 */

public class App extends Application {
    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        AppContextUtil.init(this);
//        L.init();
//        L.e("测试","sadsad");
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return mApplicationContext;
    }
}
