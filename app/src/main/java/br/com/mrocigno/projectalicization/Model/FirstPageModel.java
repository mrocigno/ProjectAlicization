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

public class FirstPageModel extends MyModel {

    public FirstPageModel(Activity activity) {
        super(activity);
    }

    public void getMangaList(String page, String limit, final FirstPageModel.MangaListCallback callback){
        getRetrofit().create(MyNetworkRoutes.class).getListMangas(page, limit).enqueue(new Callback<BaseArrayDataRemoteModel<MangaListRemoteModel>>() {
            @Override
            public void onResponse(Call<BaseArrayDataRemoteModel<MangaListRemoteModel>> call, Response<BaseArrayDataRemoteModel<MangaListRemoteModel>> response) {
                callback.onSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<BaseArrayDataRemoteModel<MangaListRemoteModel>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public boolean checkIfIsSaved(MangaListRemoteModel item){
        return getLocalData().checkIfThereIs("saved_mangas", "webid = "+ item.getId() + " AND saved = 1");
    }

    public ArrayList<Map<String, String>> getSavedMangaList(){
        return getLocalData().query("SELECT * FROM saved_mangas WHERE saved = 1", null);
    }

    public interface MangaListCallback{
        void onSuccess(ArrayList<MangaListRemoteModel> response);
        void onError(Throwable t);
    }
}
