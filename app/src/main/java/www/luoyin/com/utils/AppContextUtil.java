package www.luoyin.com.utils;

import android.content.Context;

/**
 * @author xuxiarong.
 * @time 2016/11/15 19:37.
 * @email 15889318212@163.com
 * @description
 */
public class AppContextUtil {
    private static Context sContext;

    private AppContextUtil() {

    }

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getInstance() {
        if (sContext == null) {
            throw new NullPointerException("the context is null,please init AppContextUtil in Application first.");
        }
        return sContext;
    }
}
