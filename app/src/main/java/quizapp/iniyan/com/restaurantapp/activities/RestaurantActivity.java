package quizapp.iniyan.com.restaurantapp.activities;

import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import quizapp.iniyan.com.restaurantapp.R;
import quizapp.iniyan.com.restaurantapp.adapter.RestaurantAdapter;
import quizapp.iniyan.com.restaurantapp.contracts.IrestaurantContract;
import quizapp.iniyan.com.restaurantapp.di.components.DaggerRestaurantComponent;
import quizapp.iniyan.com.restaurantapp.di.modules.ActivityModule;
import quizapp.iniyan.com.restaurantapp.di.modules.RestaurantPresenterModule;
import quizapp.iniyan.com.restaurantapp.model.RestaurantPojo;
import quizapp.iniyan.com.restaurantapp.presenter.RestaurantPresenter;


public class RestaurantActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, IrestaurantContract.view {
    @Inject
    RestaurantAdapter restaurantAdapter;

    @Inject
    RestaurantPresenter restaurantPresenter;

    @Inject
    RecyclerView.LayoutManager layoutManager;

    private ConstraintLayout root_layout;

    private RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerRestaurantComponent.builder().restaurantPresenterModule(new RestaurantPresenterModule(this)).activityModule(new ActivityModule(this)).build().inject(this);
        init();
        restaurantPresenter.loadRestaurants();
    }

    private void init() {
        root_layout = findViewById(R.id.root_layout);
        rv_list = findViewById(R.id.rv_list);
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.searchBar);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Restaurant");
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(root_layout,msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRestaurantLoaded(RestaurantPojo restaurantPojo) {
        if(restaurantPojo.getRestaurants().size()>0){
            restaurantAdapter.initializeList(restaurantPojo.getRestaurants());
            rv_list.setAdapter(restaurantAdapter);
        }
        else
            showMessage("No Data Found!");
    }
}
