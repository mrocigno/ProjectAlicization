package br.com.mrocigno.projectalicization.Model;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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

    public PagesDataRemoteModel loadLocalData(int idChapter){
        ArrayList<Map<String, String>> list = getLocalData().query("SELECT * FROM downloaded_pages WHERE id_chapter = " + idChapter, null);
        if(list.size() > 0){
            ArrayList<PagesDataRemoteModel.Pages> pages = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Map<String, String>map = list.get(i);
                pages.add(new PagesDataRemoteModel.Pages(
                        Integer.parseInt(map.get("webid")),
                        idChapter,
                        Integer.parseInt(map.get("num_page")),
                        map.get("link_page"),
                        map.get("local_path"),
                        true
                ));
            }
            return new PagesDataRemoteModel(0, pages, true);
        }else{
            return null;
        }
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
