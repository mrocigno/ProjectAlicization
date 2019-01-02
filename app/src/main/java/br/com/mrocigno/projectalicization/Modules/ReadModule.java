package br.com.mrocigno.projectalicization.Modules;

import android.app.Activity;

import br.com.mrocigno.projectalicization.Model.DetailsModel;
import br.com.mrocigno.projectalicization.Model.ReadModel;
import br.com.mrocigno.projectalicization.Presenter.DetailsInterface;
import br.com.mrocigno.projectalicization.Presenter.DetailsPresenter;
import br.com.mrocigno.projectalicization.Presenter.ReadInterface;
import br.com.mrocigno.projectalicization.Presenter.ReadPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ReadModule {
    ReadInterface view;

    public ReadModule(ReadInterface view) {
        this.view = view;
    }

    @Provides
    public ReadPresenter getPresenter(ReadModel model){
        return new ReadPresenter(view, model);
    }

    @Provides
    public ReadModel getModel(){
        return new ReadModel(view.getActivity());
    }
}
