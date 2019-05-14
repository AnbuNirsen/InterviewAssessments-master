package quizapp.iniyan.com.restaurantapp.di.modules;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import quizapp.iniyan.com.restaurantapp.adapter.RestaurantAdapter;
import quizapp.iniyan.com.restaurantapp.contracts.IrestaurantContract;
import quizapp.iniyan.com.restaurantapp.network.RetrofitClient;

@Module(includes = ActivityModule.class)
public class RestaurantPresenterModule {
    IrestaurantContract.view view;

    public RestaurantPresenterModule(IrestaurantContract.view view) {
        this.view = view;
    }

    @Provides
    public IrestaurantContract.view provideRestaurantPresenterView(){
        return view;
    }

    @Provides
    public RetrofitClient provideRetrofitClient(){
        return new RetrofitClient();
    }

    @Provides
    public RestaurantAdapter provideRestaurantAdapter(){
        return new RestaurantAdapter();
    }

    @Provides
    public RecyclerView.LayoutManager provideLayoutManager(Activity activity){
        return new LinearLayoutManager(activity);
    }
}
