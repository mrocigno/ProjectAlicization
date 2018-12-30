package br.com.mrocigno.projectalicization.Components;

import br.com.mrocigno.projectalicization.Modules.SplashModule;
import br.com.mrocigno.projectalicization.View.SplashActivity;
import dagger.Component;

@Component(modules = {SplashModule.class})
public interface SplashComponent {
    void inject(SplashActivity activity);
}
