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
    public FirstPageFragment getFirstPageFragment(){
        return FirstPageFragment.newInstance();
    }

    @Provides
    public FirstPageModel getFirstPageModel(){
        return new FirstPageModel(view.getActivity());
    }

    @Provides
    public FirstPagePresenter getFirstPagePresenter(FirstPageFragment firstPageFragment,FirstPageModel model){
        return new FirstPagePresenter(firstPageFragment, model, view);
    }

    @Provides
    @Singleton
    public DownloadPageFragment getDownloadPageFragment(){
        return DownloadPageFragment.newInstance();
    }

    @Provides
    public DownloadPageModel getDownloadPageModel(){
        return new DownloadPageModel(view.getActivity());
    }

    @Provides
    public DownloadPagePresenter getDownloadPagePresenter(DownloadPageFragment downloadPageFragment, DownloadPageModel model){
        return new DownloadPagePresenter(downloadPageFragment, model, view);
    }

}
