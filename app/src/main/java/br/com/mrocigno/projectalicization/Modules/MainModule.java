package br.com.mrocigno.projectalicization.Modules;

import java.util.ArrayList;

import javax.inject.Singleton;

import br.com.mrocigno.projectalicization.Model.DownloadPageModel;
import br.com.mrocigno.projectalicization.Model.FirstPageModel;
import br.com.mrocigno.projectalicization.Model.MainModel;
import br.com.mrocigno.projectalicization.Presenter.DownloadPagePresenter;
import br.com.mrocigno.projectalicization.Presenter.FirstPageInterface;
import br.com.mrocigno.projectalicization.Presenter.FirstPagePresenter;
import br.com.mrocigno.projectalicization.Presenter.MainInterface;
import br.com.mrocigno.projectalicization.Presenter.MainPresenter;
import br.com.mrocigno.projectalicization.View.DownloadPageFragment;
import br.com.mrocigno.projectalicization.View.FirstPageFragment;
import br.com.mrocigno.projectalicization.View.MainActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    MainInterface view;


    public MainModule(MainInterface view) {
        this.view = view;
    }

    @Provides
    public MainPresenter getPresenter(MainModel model){
        return new MainPresenter(view, model);
    }

    @Provides
    public MainModel getModel(){
        return new MainModel(view.getActivity());
    }

    @Provides
    @Singleton
    public FirstPageFragment getFirstPageFragment(FirstPagePresenter presenter){
        return new FirstPageFragment().newInstance(presenter);
    }

    @Provides
    public FirstPageModel getFirstPageModel(){
        return new FirstPageModel(view.getActivity());
    }

    @Provides
    public FirstPagePresenter getFirstPagePresenter(FirstPageModel model){
        return new FirstPagePresenter(model, view);
    }

    @Provides
    @Singleton
    public DownloadPageFragment getDownloadPageFragment(DownloadPagePresenter presenter){
        return new DownloadPageFragment().newInstance(presenter);
    }

    @Provides
    public DownloadPageModel getDownloadPageModel(){
        return new DownloadPageModel(view.getActivity());
    }

    @Provides
    public DownloadPagePresenter getDownloadPagePresenter(DownloadPageModel model){
        return new DownloadPagePresenter(model, view);
    }

}
