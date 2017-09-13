package bigstylist.recyclerviewsample1;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ${BigStylist} on 19/8/17.
 */

public interface ApiService {

    @GET("articles?source=google-news&sortBy=top&apiKey=28ec67e78d9b48f6a32f0ecfc70ea308")
    Call<NewsModel> getArticles();

}

