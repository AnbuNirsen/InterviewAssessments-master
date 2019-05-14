package quizapp.iniyan.com.restaurantapp.presenter;

import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import quizapp.iniyan.com.restaurantapp.Utils.RxDisposables;
import quizapp.iniyan.com.restaurantapp.contracts.IrestaurantDetailsContract;
import quizapp.iniyan.com.restaurantapp.contracts.IrestaurantDetailsContract;
import quizapp.iniyan.com.restaurantapp.model.RestaurantPojo;
import quizapp.iniyan.com.restaurantapp.model.Restaurant_;
import quizapp.iniyan.com.restaurantapp.network.RetrofitClient;
import retrofit2.Response;

public class RestaurantDetailsPresenter extends RxDisposables<IrestaurantDetailsContract.view> implements IrestaurantDetailsContract.presenter {
    IrestaurantDetailsContract.view view;

    private static final String TAG = "RestaurantPresenter";

    @Inject
    RetrofitClient retrofitClient;

    @Inject
    public RestaurantDetailsPresenter(IrestaurantDetailsContract.view view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadRestaurants(int resId) {
        Log.d(TAG, "loadRestaurants: "+resId);
        addCompositeDisposable(retrofitClient.getApiInterface().getRestaurantsById(String.valueOf(resId))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<Response<Restaurant_>>() {
                            @Override
                            public void accept(Response<Restaurant_> restaurant_response) throws Exception {
                                Log.d(TAG, "accept: "+restaurant_response.body().toString());
                                if(restaurant_response.isSuccessful()){
                                    if(restaurant_response.body()!=null)
                                        view.onRestaurantLoaded(restaurant_response.body());
                                    else
                                        view.showMessage("No Data Found!");
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                if (throwable instanceof IOException) {
                                    view.showMessage("Check Your Internet Conncetion!");
                                } else if (throwable instanceof IllegalStateException) {
                                    view.showMessage("Couldn\\'t fetch data from server.");
                                } else {
                                    view.showMessage("Server Error! Please Try again Later!.");
                                }
                            }
                        })
        );
    }
}
