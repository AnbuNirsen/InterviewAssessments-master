package quizapp.iniyan.com.restaurantapp.contracts;

import quizapp.iniyan.com.restaurantapp.model.RestaurantPojo;
import quizapp.iniyan.com.restaurantapp.model.Restaurant_;

public interface IrestaurantDetailsContract {
    interface view{
        void showMessage(String msg);
        void onRestaurantLoaded(Restaurant_ restaurant_);
    }
    interface presenter{
        void loadRestaurants(int resId);
    }
}
