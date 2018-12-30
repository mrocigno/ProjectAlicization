package br.com.mrocigno.projectalicization.Components;

import br.com.mrocigno.projectalicization.Config.MyModel;
import br.com.mrocigno.projectalicization.Modules.DataModule;
import dagger.Component;

@Component(modules = {DataModule.class})
public interface ModelComponent {
    void inject(MyModel model);
}
