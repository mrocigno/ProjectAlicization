package br.com.mrocigno.projectalicization.Model;

import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;

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

    public void getMangaList(String page, String limit, final MangaListCallback callback){
        getRetrofit().create(MyNetworkRoutes.class).getListMangas(page, limit).enqueue(new Callback<BaseArrayDataRemoteModel<MangaListRemoteModel>>() {
            @Override
            public void onResponse(Call<BaseArrayDataRemoteModel<MangaListRemoteModel>> call, Response<BaseArrayDataRemoteModel<MangaListRemoteModel>> response) {
                ArrayList<MangaListRemoteModel> list = response.body().getData();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSaved(getLocalData().checkIfThereIs("saved_mangas", "webid = "+list.get(i).getWebid() + " AND saved = 1"));
                }
                callback.onSuccess(list);
            }

            @Override
            public void onFailure(Call<BaseArrayDataRemoteModel<MangaListRemoteModel>> call, Throwable t) {
                callback.onError(t);
            }
        });
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

    public ArrayList<Map<String, String>> getSavedMangaList(){
        return getLocalData().query("SELECT * FROM saved_mangas WHERE saved = 1", null);
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

    public interface MangaListCallback{
        void onSuccess(ArrayList<MangaListRemoteModel> response);
        void onError(Throwable t);
    }

    public interface MangaSearchCallback{
        void onSearchSuccess(ArrayList<MangaListRemoteModel> response);
        void onSearchError(Throwable t);
    }
}
