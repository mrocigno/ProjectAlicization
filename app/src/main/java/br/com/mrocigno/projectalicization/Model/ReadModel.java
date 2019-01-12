package br.com.mrocigno.projectalicization.Model;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;

import br.com.mrocigno.projectalicization.Config.MyModel;
import br.com.mrocigno.projectalicization.Config.MyNetworkRoutes;
import br.com.mrocigno.projectalicization.RemoteModels.BaseRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadModel extends MyModel {
    Activity activity;

    public ReadModel(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void loadData(int id, final DataCallback callback){
        getRetrofit().create(MyNetworkRoutes.class).getPagesData(id).enqueue(new Callback<BaseRemoteModel<PagesDataRemoteModel>>() {
            @Override
            public void onResponse(Call<BaseRemoteModel<PagesDataRemoteModel>> call, Response<BaseRemoteModel<PagesDataRemoteModel>> response) {
                callback.onDataSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<BaseRemoteModel<PagesDataRemoteModel>> call, Throwable t) {
                callback.onDataError(t);
            }
        });
    }

    public interface DataCallback{
        void onDataSuccess(PagesDataRemoteModel data);
        void onDataError(Throwable t);
    }
}
