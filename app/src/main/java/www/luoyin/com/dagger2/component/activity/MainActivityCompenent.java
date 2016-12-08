package www.luoyin.com.dagger2.component.activity;

import dagger.Component;
import www.luoyin.com.MainActivity;
import www.luoyin.com.dagger2.module.activity.MianActivityModule;

/**
 * @author xuxiarong.
 * @time 2016/11/15 22:34.
 * @email 15889318212@163.com
 * @description
 */
@Component(modules = MianActivityModule.class)
public interface MainActivityCompenent {
    void compenent(MainActivity mainActivity);
}
