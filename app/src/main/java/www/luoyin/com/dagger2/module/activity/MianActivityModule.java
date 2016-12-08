package www.luoyin.com.dagger2.module.activity;

import dagger.Module;
import dagger.Provides;
import www.luoyin.com.MainActivity;

/**
 * @author xuxiarong.
 * @time 2016/11/15 22:30.
 * @email 15889318212@163.com
 * @description
 */

@Module
public class MianActivityModule {

    MainActivity mMainActivity;
    public MianActivityModule(MainActivity mMainActivity){
        this.mMainActivity = mMainActivity;
    }
    @Provides
    MainActivity provideMainActivity(){
        return mMainActivity;
    }

}
//@Module
//public class HomeFragmentModule {
//    HomeFragment fragment;
//
//    public HomeFragmentModule(HomeFragment fragment) {
//        this.fragment = fragment;
//    }
//
//    @Provides
//    HomeFragment provideHomeFragment(){
//        return fragment;
//    }
//}