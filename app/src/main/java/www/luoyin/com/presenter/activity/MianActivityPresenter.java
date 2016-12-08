package www.luoyin.com.presenter.activity;

import javax.inject.Inject;

import www.luoyin.com.MainActivity;
import www.luoyin.com.presenter.base.BasePresenter;

/**
 * @author xuxiarong.
 * @time 2016/11/15 22:26.
 * @email 15889318212@163.com
 * @description
 */

public class MianActivityPresenter extends BasePresenter {

    MainActivity mMainActivity;

    @Inject
    public MianActivityPresenter(MainActivity mainActivity){
        this.mMainActivity = mainActivity;
    }

    public void netOpretor(){

    }

    @Override
    protected void isLoading(boolean isLoading) {
        mMainActivity.isLoading(isLoading);
    }

    @Override
    protected void DoSuccess() {

        mMainActivity.DoSuccess();
    }

    @Override
    protected void showError(boolean isError,String Reson) {

        mMainActivity.isError(isError,Reson);
    }

    @Override
    public void getData() {
        netOpretor(mLuoYInService.getLatestNews());
    }

    @Override
    protected void unsubscribe() {

    }

}
