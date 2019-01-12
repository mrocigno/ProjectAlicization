package br.com.mrocigno.projectalicization.Model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Map;

import br.com.mrocigno.projectalicization.Config.MyModel;
import br.com.mrocigno.projectalicization.Config.MyNetworkRoutes;
import br.com.mrocigno.projectalicization.RemoteModels.BaseArrayDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel extends MyModel {

    Activity activity;
    public MainModel(Activity activity) {
        super(activity);
        this.activity = activity;
    }



    public void searchManga(String name, final MangaSearchCallback callback){
        getRetrofit().create(MyNetworkRoutes.class).searchMangas(name).enqueue(new Callback<BaseArrayDataRemoteModel<MangaListRemoteModel>>() {
            @Override
            public void onResponse(Call<BaseArrayDataRemoteModel<MangaListRemoteModel>> call, Response<BaseArrayDataRemoteModel<MangaListRemoteModel>> response) {
                callback.onSearchSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<BaseArrayDataRemoteModel<MangaListRemoteModel>> call, Throwable t) {
                callback.onSearchError(t);
            }
        });
    }

    public boolean checkIfIsSaved(MangaListRemoteModel item){
        return getLocalData().checkIfThereIs("saved_mangas", "webid = "+ item.getId() + " AND saved = 1");
    }

    public ArrayList<Map<String, String>> getSavedMangaList(){
        return getLocalData().query("SELECT * FROM saved_mangas WHERE saved = 1", null);
    }

    public interface MangaSearchCallback{
        void onSearchSuccess(ArrayList<MangaListRemoteModel> response);
        void onSearchError(Throwable t);
    }
}
