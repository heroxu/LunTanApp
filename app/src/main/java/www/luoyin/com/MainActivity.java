package www.luoyin.com;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import www.luoyin.com.dagger2.component.activity.DaggerMainActivityCompenent;
import www.luoyin.com.dagger2.module.activity.MianActivityModule;
import www.luoyin.com.presenter.activity.MianActivityPresenter;
import www.luoyin.com.ui.activity.base.BaseActivity;



public class MainActivity extends BaseActivity {

    @Inject
    public MianActivityPresenter mMianActivityPresenter;


    private void setComponent() {
        DaggerMainActivityCompenent.builder().mianActivityModule(new MianActivityModule(this)).build().compenent(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    
    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        //Dagger2依赖注入的操作
        setComponent();
        //MainActivity的Presenter发起业务层的操作，凡是涉及到数据业务层的操作，都需要写到MianActivityPresenter里
        mMianActivityPresenter.getData();

    }


    //是否支持右滑结束界面
    @Override
    protected boolean isSwipeClose() {
        return true;
    }
    //网络访问成功后需要做的事
    public void DoSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Toast.makeText(this, "访问成功", Toast.LENGTH_LONG).show();
        isError(true,"aaaa");
    }

}
