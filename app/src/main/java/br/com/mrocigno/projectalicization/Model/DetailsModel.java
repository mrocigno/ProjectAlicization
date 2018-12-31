package br.com.mrocigno.projectalicization.Model;

import android.app.Activity;

import br.com.mrocigno.projectalicization.Config.MyModel;
import br.com.mrocigno.projectalicization.Config.MyNetworkRoutes;
import br.com.mrocigno.projectalicization.RemoteModels.BaseRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaDetailsRemoteModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsModel extends MyModel {

    public DetailsModel(Activity activity) {
        super(activity);
    }

    public boolean isSaved(int webid){
        return getLocalData().checkIfThereIs("saved_mangas", "saved = 1 and webid = "+ webid);
    }

    public void getMangaDetails(String link, final MangaDetailsCallback callback){
        getRetrofit().create(MyNetworkRoutes.class).getMangaDetails(link).enqueue(new Callback<BaseRemoteModel<MangaDetailsRemoteModel>>() {
            @Override
            public void onResponse(Call<BaseRemoteModel<MangaDetailsRemoteModel>> call, Response<BaseRemoteModel<MangaDetailsRemoteModel>> response) {
                callback.onDetailsSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<BaseRemoteModel<MangaDetailsRemoteModel>> call, Throwable t) {
                callback.onDetailsError(t);
            }
        });
    }

    public interface MangaDetailsCallback{
        void onDetailsSuccess(MangaDetailsRemoteModel item);
        void onDetailsError(Throwable t);
    }

}
