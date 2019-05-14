package quizapp.iniyan.com.restaurantapp.di.modules;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import quizapp.iniyan.com.restaurantapp.adapter.RestaurantAdapter;
import quizapp.iniyan.com.restaurantapp.contracts.IrestaurantDetailsContract;
import quizapp.iniyan.com.restaurantapp.network.RetrofitClient;

@Module(includes = ActivityModule.class)
public class RestaurantDetailsPresenterModule {
    IrestaurantDetailsContract.view view;

    public RestaurantDetailsPresenterModule(IrestaurantDetailsContract.view view) {
        this.view = view;
    }

    @Provides
    public IrestaurantDetailsContract.view provideRestaurantPresenterView(){
        return view;
    }

    @Provides
    public RetrofitClient provideRetrofitClient(){
        return new RetrofitClient();
    }
    
}
