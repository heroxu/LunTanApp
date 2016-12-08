package www.luoyin.com.presenter.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import www.luoyin.com.model.net.api.LuoYInService;
import www.luoyin.com.model.net.bean.NewsList;
import www.luoyin.com.utils.NetUtil;

/**
 * @author xuxiarong.
 * @time 2016/11/14 20:42.
 * @email 15889318212@163.com
 * @description MVP的BasePresenter，处理一些通用模块的业务逻辑操作
 * BasePresenter里面将Rxjava所做的重复操作提取出来:解析服务器返回通用数据(code，ErrorReson),线程切换，错误提示等等
 * 实现类只要重写显示进度条，网络访问成功，显示错误信息三个方法即可，流程无须关心
 */

public abstract class BasePresenter {
    //TODO 联网的一些通用操作

    public static final String BASE_ZHIHU_URL = "http://news-at.zhihu.com/api/4/";//BaseURL
    public static final int CACHE_STALE_SHORT = 60;    //短缓存有效期为1分钟
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;    //长缓存有效期为7天
    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";//请求头缓存信息
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";

    protected static OkHttpClient mOkHttpClient;
    protected static LuoYInService mLuoYInService;
    protected static Subscription mSubscription;


    public BasePresenter(){
        initRetrofit();

    }

//    ThreadPoolExecutor AtivityManager Ati
//    Handler Message ,Looper, MessageQueue

    private void initRetrofit() {
        initOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_ZHIHU_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mLuoYInService = retrofit.create(LuoYInService.class);
    }

    //，
    //


    //需要子类重写的方法，一旦子类中有业务数据跟界面交互，必须重写该方法
    protected    void netOpretor(Observable<NewsList> observable){

        //如果订阅者不为空，而且已经绑定过了观察者，需要解绑，减少观察者对象的数量，防止多次绑定，内存泄露
        if(mSubscription != null && mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }

        mSubscription  =observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //显示进度条，需要子类重写
                        isLoading(true);

                    }
                }).map(new Func1<NewsList, NewsList>() {
                    @Override
                    public NewsList call(NewsList userBean) {
                        return userBean;
                    }
                }).subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList UserBean) {
                        if(UserBean!=null){
                            //网络访问成功，需要子类重写该方法
                            isLoading(false);
                            DoSuccess();
                        }else{
                            //网络结果访问失败，这里是服务器规定的一些错误信息，例如登陆时用户名，密码不正确
                            isLoading(false);
                            showError(true,"");
                        }
                    }
                },new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //网络结果访问失败，这里是一些网络的因素，例如断网，服务器404
                        isLoading(false);
                        showError(true,throwable.getMessage());
                    }
                });
    }

    protected abstract void isLoading(boolean isLoading);

    protected abstract void DoSuccess();

    protected abstract void showError(boolean isError,String Reson);

    protected abstract void getData();

    protected abstract void unsubscribe();


    private void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (BasePresenter.class) {
                if (mOkHttpClient == null) {

                    // 指定缓存路径,缓存大小100Mb
//                    Cache cache = new Cache(new File(App.getContext().getCacheDir(), "HttpCache"),
//                            1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
//                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG)
                        .removeHeader("Pragma").build();
            }
        }
    };
}
