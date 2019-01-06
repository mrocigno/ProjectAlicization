package br.com.mrocigno.projectalicization.Config;

import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Application;
import br.com.mrocigno.projectalicization.Components.DaggerModelComponent;
import br.com.mrocigno.projectalicization.Database.LocalData;
import br.com.mrocigno.projectalicization.Model.ReadModel;
import br.com.mrocigno.projectalicization.Modules.DataModule;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
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

    public void saveManga(MangaListRemoteModel item, boolean save){
        int saved = save? 1:0;
        ContentValues cv = new ContentValues();
        if(!getLocalData().checkIfThereIs("saved_mangas", "webid = "+ item.getWebid())) {
            cv.put("name", item.getName());
            cv.put("cover", item.getCover());
            cv.put("link", item.getLink());
            cv.put("saved", saved);
            cv.put("webid", item.getWebid());
            getLocalData().insert("saved_mangas", cv);
            Log.e("TESTEEE", "saveManga: " + "insert");
        }else{
            Log.e("TESTEEE", "saveManga: " + "updated");
            cv.put("saved", saved);
            getLocalData().update("saved_mangas", cv, "webid = " + item.getWebid());
        }
    }
}
