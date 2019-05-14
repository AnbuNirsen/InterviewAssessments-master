package quizapp.iniyan.com.restaurantapp.presenter;

import android.support.design.widget.Snackbar;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import quizapp.iniyan.com.restaurantapp.R;
import quizapp.iniyan.com.restaurantapp.Utils.RxDisposables;
import quizapp.iniyan.com.restaurantapp.contracts.IrestaurantContract;
import quizapp.iniyan.com.restaurantapp.model.RestaurantPojo;
import quizapp.iniyan.com.restaurantapp.network.RetrofitClient;
import retrofit2.Response;

public class RestaurantPresenter extends RxDisposables<IrestaurantContract.view> implements IrestaurantContract.presenter {
    IrestaurantContract.view view;

    private static final String TAG = "RestaurantPresenter";

    @Inject
    RetrofitClient retrofitClient;

    @Inject
    public RestaurantPresenter(IrestaurantContract.view view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadRestaurants() {
        addCompositeDisposable(retrofitClient.getApiInterface().getRestaurants()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<Response<RestaurantPojo>>() {
                            @Override
                            public void accept(Response<RestaurantPojo> restaurantPojoResponse) throws Exception {
                                Log.d(TAG, "accept: ==>1");
                                if(restaurantPojoResponse.isSuccessful()){
                                    if(restaurantPojoResponse.body()!=null)
                                        view.onRestaurantLoaded(restaurantPojoResponse.body());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d(TAG, "accept: ==>2"+throwable.getMessage());
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
