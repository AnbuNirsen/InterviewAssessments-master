package quizapp.iniyan.com.restaurantapp.contracts;

import quizapp.iniyan.com.restaurantapp.model.RestaurantPojo;

public interface IrestaurantContract {
    interface view{
        void showMessage(String msg);
        void onRestaurantLoaded(RestaurantPojo restaurantPojo);
    }
    interface presenter{
        void loadRestaurants();
    }
}
