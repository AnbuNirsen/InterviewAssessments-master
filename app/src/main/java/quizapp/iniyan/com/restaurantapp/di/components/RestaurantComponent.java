package quizapp.iniyan.com.restaurantapp.di.components;

import dagger.Component;
import quizapp.iniyan.com.restaurantapp.activities.RestaurantActivity;
import quizapp.iniyan.com.restaurantapp.di.modules.RestaurantPresenterModule;

@Component(modules = RestaurantPresenterModule.class)
public interface RestaurantComponent {
    void inject(RestaurantActivity restaurantActivity);
}
