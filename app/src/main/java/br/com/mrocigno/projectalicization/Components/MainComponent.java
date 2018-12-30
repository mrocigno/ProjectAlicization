package br.com.mrocigno.projectalicization.Components;

import br.com.mrocigno.projectalicization.Modules.MainModule;
import br.com.mrocigno.projectalicization.View.MainActivity;
import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
