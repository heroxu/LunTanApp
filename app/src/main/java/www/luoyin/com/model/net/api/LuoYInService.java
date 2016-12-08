package www.luoyin.com.model.net.api;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;
import www.luoyin.com.model.dao.bean.UserBean;
import www.luoyin.com.model.net.bean.NewsList;
import www.luoyin.com.presenter.base.BasePresenter;

/**
 * @author xuxiarong.
 * @time 2016/11/15 19:38.
 * @email 15889318212@163.com
 * @description
 */

public interface LuoYInService {
    @Headers(BasePresenter.CACHE_CONTROL_AGE + BasePresenter.CACHE_STALE_SHORT)
    @GET("stories/latest")
    Observable<NewsList> getLatestNews();

    @Headers(BasePresenter.CACHE_CONTROL_AGE + BasePresenter.CACHE_STALE_LONG)
    @GET("stories/before/{date}")
    Observable<NewsList> getBeforeNews(@Path("date") String date);

    @Headers(BasePresenter.CACHE_CONTROL_AGE + BasePresenter.CACHE_STALE_LONG)
    @GET("story/{id}")
    Observable<UserBean> getNewsDetail(@Path("id") int id);
}
