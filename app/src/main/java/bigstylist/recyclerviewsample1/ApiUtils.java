package bigstylist.recyclerviewsample1;

/**
 * Created by ${BigStylist} on 19/8/17.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://newsapi.org/v1/";

    public static ApiService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
