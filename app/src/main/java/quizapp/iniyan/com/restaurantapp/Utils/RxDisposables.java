package quizapp.iniyan.com.restaurantapp.Utils;

import android.view.View;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxDisposables<V> {
    protected final V view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public RxDisposables(V view) {
        this.view = view;
    }
    protected void addCompositeDisposable(Disposable d){
        compositeDisposable.add(d);
    }
}