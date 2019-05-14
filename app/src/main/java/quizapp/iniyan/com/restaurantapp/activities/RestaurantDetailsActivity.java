package quizapp.iniyan.com.restaurantapp.activities;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import quizapp.iniyan.com.restaurantapp.R;
import quizapp.iniyan.com.restaurantapp.contracts.IrestaurantDetailsContract;
import quizapp.iniyan.com.restaurantapp.di.components.DaggerRestaurantDetailsComponent;
import quizapp.iniyan.com.restaurantapp.di.modules.ActivityModule;
import quizapp.iniyan.com.restaurantapp.di.modules.RestaurantDetailsPresenterModule;
import quizapp.iniyan.com.restaurantapp.model.RestaurantPojo;
import quizapp.iniyan.com.restaurantapp.model.Restaurant_;
import quizapp.iniyan.com.restaurantapp.presenter.RestaurantDetailsPresenter;
import quizapp.iniyan.com.restaurantapp.presenter.RestaurantPresenter;

public class RestaurantDetailsActivity extends AppCompatActivity implements IrestaurantDetailsContract.view {
private Toolbar anim_toolbar;
private AppBarLayout appBarLayout;
private CollapsingToolbarLayout collapsingToolbarLayout ;
private ImageView header;
private TextView tv_restaurant_name,tv_rating,tv_address,tv_city,tv_cuisens_texts,tv_avg_cost;
private FloatingActionButton fab_locate_me;
private int resId;
private String name;

    @Inject
    RestaurantDetailsPresenter restaurantDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        DaggerRestaurantDetailsComponent.builder().activityModule(new ActivityModule(this)).restaurantDetailsPresenterModule(new RestaurantDetailsPresenterModule(this)).build().inject(this);

        Intent i = getIntent();
        resId = i.getIntExtra("resId",0);
        name = i.getStringExtra("resName");

        init();

        toolBar();

        restaurantDetailsPresenter.loadRestaurants(resId);
    }

    private void toolBar() {
        anim_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(name);
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
                    anim_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    anim_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
                    isShow = false;
                }
            }
        });
    }

    private void init() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        anim_toolbar = findViewById(R.id.anim_toolbar);
        header = findViewById(R.id.header);
        tv_restaurant_name = findViewById(R.id.tv_restaurant_name);
        tv_rating = findViewById(R.id.tv_rating);
        tv_address = findViewById(R.id.tv_address);
        tv_city = findViewById(R.id.tv_city);
        tv_cuisens_texts = findViewById(R.id.tv_cuisens_texts);
        tv_avg_cost = findViewById(R.id.tv_avg_cost);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestaurantLoaded(Restaurant_ restaurant_) {
        if(restaurant_!=null){
            if(!TextUtils.isEmpty(restaurant_.getThumb())){
             //load image from internet
             Picasso.with(context).load(restaurant_.getThumb()).into(header)   
            }
            if(!TextUtils.isEmpty(restaurant_.getName()))
                tv_restaurant_name.setText(restaurant_.getName());
            if(restaurant_.getUserRating().getAggregateRating() != 0.0)
                tv_rating.setText(String.valueOf(restaurant_.getUserRating().getAggregateRating())+" / 5.0");
            else
                tv_rating.setVisibility(View.GONE);

            if(!TextUtils.isEmpty(restaurant_.getLocation().getAddress()))
                tv_address.setText(restaurant_.getLocation().getAddress());
            if(!TextUtils.isEmpty(restaurant_.getLocation().getCity()))
                tv_city.setText(restaurant_.getLocation().getCity());
            if(!TextUtils.isEmpty(restaurant_.getCuisines())){
                String cuisines[] = restaurant_.getCuisines().split(",");
                String ab = restaurant_.getCuisines().replace(",","\n -");
                tv_cuisens_texts.setText(ab);
            }
            tv_avg_cost.setText(String.valueOf(restaurant_.getAverageCostForTwo()));

        }
    }


}
