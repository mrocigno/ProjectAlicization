package br.com.mrocigno.projectalicization.Modules;

import android.app.Activity;

import br.com.mrocigno.projectalicization.Model.DetailsModel;
import br.com.mrocigno.projectalicization.Presenter.DetailsInterface;
import br.com.mrocigno.projectalicization.Presenter.DetailsPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {
    DetailsInterface view;
    Activity activity;

    public DetailsModule(DetailsInterface view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Provides
    public DetailsPresenter getPresenter(DetailsModel model){
        return new DetailsPresenter(view, model);
    }

    @Provides
    public DetailsModel getModel(){
        return new DetailsModel(activity);
    }
}
