package quizapp.iniyan.com.restaurantapp.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import quizapp.iniyan.com.restaurantapp.R;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_name,tv_address,tv_rating;
    public ImageView img_restaurant;
    public Button btn_view;
    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_address = itemView.findViewById(R.id.tv_address);
        tv_rating = itemView.findViewById(R.id.tv_rating);
        img_restaurant = itemView.findViewById(R.id.img_restaurant);
        btn_view = itemView.findViewById(R.id.btn_view);
    }
}
