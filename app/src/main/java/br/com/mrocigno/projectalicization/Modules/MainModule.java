package br.com.mrocigno.projectalicization.Modules;

import android.app.Activity;

import br.com.mrocigno.projectalicization.Model.MainModel;
import br.com.mrocigno.projectalicization.Presenter.MainInterface;
import br.com.mrocigno.projectalicization.Presenter.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    MainInterface view;
    Activity activity;

    public MainModule(MainInterface view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Provides
    public MainPresenter getPresenter(MainModel model){
        return new MainPresenter(view, model);
    }

    @Provides
    public MainModel getModel(){
        return new MainModel(activity);
    }
}
