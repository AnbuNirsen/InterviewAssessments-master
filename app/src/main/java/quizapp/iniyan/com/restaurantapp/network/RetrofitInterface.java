package quizapp.iniyan.com.restaurantapp.network;


import io.reactivex.Observable;
import quizapp.iniyan.com.restaurantapp.model.RestaurantPojo;
import quizapp.iniyan.com.restaurantapp.model.Restaurant_;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static quizapp.iniyan.com.restaurantapp.Utils.Constants.HEADER_KEY;
import static quizapp.iniyan.com.restaurantapp.Utils.Constants.HEADER_VALUE;

public interface RetrofitInterface {
    @Headers(HEADER_KEY+":"+HEADER_VALUE)
    @GET("search")
    Observable<Response<RestaurantPojo>> getRestaurants();

    @Headers(HEADER_KEY+":"+HEADER_VALUE)
    @GET("restaurant")
    Observable<Response<Restaurant_>> getRestaurantsById(@Query("res_id")String res_id);
}
