package br.com.mrocigno.projectalicization.Components;

import br.com.mrocigno.projectalicization.Modules.DetailsModule;
import br.com.mrocigno.projectalicization.View.DetailsActivity;
import dagger.Component;

@Component(modules = {DetailsModule.class})
public interface DetailsComponent {
    void inject(DetailsActivity activity);
}
