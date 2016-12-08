package www.luoyin.com.model.net.rxjava;

import android.view.View;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author xuxiarong.
 * @time 2016/12/2 12:56.
 * @email 15889318212@163.com
 * @description
 */

public class RxHelper {
    //指定Rx线程切换

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
//    public static <T> Observable <T> compase(){
//        return new Observable<>().compose()
//    }

    public <T extends View> T mothed(){

        return null;
    }

}
