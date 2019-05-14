package quizapp.iniyan.com.restaurantapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import quizapp.iniyan.com.restaurantapp.R;
import quizapp.iniyan.com.restaurantapp.activities.RestaurantActivity;
import quizapp.iniyan.com.restaurantapp.activities.RestaurantDetailsActivity;
import quizapp.iniyan.com.restaurantapp.model.Restaurant;
import quizapp.iniyan.com.restaurantapp.viewHolder.RestaurantViewHolder;

public class RestaurantAdapter  extends RecyclerView.Adapter<RestaurantViewHolder> {
    List<Restaurant> restaurants;
    Context context;
    private static final String TAG = "RestaurantAdapter";

    @Inject
    public RestaurantAdapter(){

    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        return new RestaurantViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ow_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, final int i) {
        restaurantViewHolder.tv_name.setText(restaurants.get(i).getRestaurant().getName());
        restaurantViewHolder.tv_address.setText(restaurants.get(i).getRestaurant().getLocation().getAddress());
        restaurantViewHolder.tv_rating.setText(String.valueOf(restaurants.get(i).getRestaurant().getUserRating().getAggregateRating()));
        restaurantViewHolder.btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+restaurants.get(i).getRestaurant().getR().getResId());
                Intent moveToRestaurant = new Intent(context, RestaurantDetailsActivity.class);
                moveToRestaurant.putExtra("resId",restaurants.get(i).getRestaurant().getR().getResId());
                moveToRestaurant.putExtra("resName",restaurants.get(i).getRestaurant().getName());
                context.startActivity(moveToRestaurant);
            }
        });
    }

    public void initializeList(List<Restaurant> restaurants){
        this.restaurants = restaurants;
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
