package bigstylist.recyclerviewsample1;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ${BigStylist} on 13/9/17.
 */

public class MainPresenter {

    public ArticleListener mArticleListener;
    public static final String TAG = MainPresenter.class.getSimpleName();
    private ApiService mApiService;

    public interface ArticleListener {
        void returnArticles(List<NewsModel.Article> articles);
        void returnError(String errorMessage);
    }

    public MainPresenter(ArticleListener mArticleListener) {
        mApiService = ApiUtils.getSOService();
        this.mArticleListener = mArticleListener;
    }

    public void getArticles(){
        mApiService.getArticles().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                Log.e(TAG, new Gson().toJson(response));
                try {
                    if (null != response && response.isSuccessful() && response.body().getStatus().toUpperCase().equals("OK") && response.body().getArticles().size() > 0) {
                        mArticleListener.returnArticles(response.body().getArticles());
                    }else{
                        mArticleListener.returnError("Error");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    mArticleListener.returnError("Error");
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                mArticleListener.returnError("Error");
            }
        });
    }
}

