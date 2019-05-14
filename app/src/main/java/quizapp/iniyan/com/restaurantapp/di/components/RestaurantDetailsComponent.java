package quizapp.iniyan.com.restaurantapp.di.components;

import dagger.Component;
import quizapp.iniyan.com.restaurantapp.activities.RestaurantActivity;
import quizapp.iniyan.com.restaurantapp.activities.RestaurantDetailsActivity;
import quizapp.iniyan.com.restaurantapp.di.modules.RestaurantDetailsPresenterModule;
import quizapp.iniyan.com.restaurantapp.di.modules.RestaurantPresenterModule;

@Component(modules = RestaurantDetailsPresenterModule.class)
public interface RestaurantDetailsComponent {
    void inject(RestaurantDetailsActivity restaurantDetailsActivity);
}
