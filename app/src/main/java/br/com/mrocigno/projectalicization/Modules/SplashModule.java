package br.com.mrocigno.projectalicization.Modules;

import br.com.mrocigno.projectalicization.Presenter.SplashInterface;
import br.com.mrocigno.projectalicization.Presenter.SplashPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {
    SplashInterface view;

    public SplashModule(SplashInterface view) {
        this.view = view;
    }

    @Provides
    public SplashPresenter getPresenter(){
        return new SplashPresenter(view);
    }
}
