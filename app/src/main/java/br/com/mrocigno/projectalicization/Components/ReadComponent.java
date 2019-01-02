package br.com.mrocigno.projectalicization.Components;

import br.com.mrocigno.projectalicization.Modules.ReadModule;
import br.com.mrocigno.projectalicization.View.ReadActivity;
import dagger.Component;

@Component(modules = {ReadModule.class})
public interface ReadComponent {
    void inject(ReadActivity activity);
}
