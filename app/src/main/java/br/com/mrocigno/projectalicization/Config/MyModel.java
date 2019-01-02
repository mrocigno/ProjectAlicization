package br.com.mrocigno.projectalicization.Config;

import android.app.Activity;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Application;
import br.com.mrocigno.projectalicization.Components.DaggerModelComponent;
import br.com.mrocigno.projectalicization.Database.LocalData;
import br.com.mrocigno.projectalicization.Model.ReadModel;
import br.com.mrocigno.projectalicization.Modules.DataModule;
import br.com.mrocigno.projectalicization.View.ReadActivity;
import retrofit2.Retrofit;

public class MyModel {
    @Inject
    Retrofit retrofit;

    @Inject
    Retrofit.Builder retrofitBuilder;

    @Inject
    LocalData localData;

    public MyModel(Activity activity) {
        DaggerModelComponent
                .builder()
                .dataModule(new DataModule(activity))
                .build()
                .inject(this);

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public LocalData getLocalData() {
        return localData;
    }

    public Retrofit getRetrofitDownloader(){
        return retrofitBuilder.build();
    }
}
