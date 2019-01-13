package br.com.mrocigno.projectalicization.Model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import br.com.mrocigno.projectalicization.Config.MyModel;
import br.com.mrocigno.projectalicization.Config.MyNetworkRoutes;
import br.com.mrocigno.projectalicization.RemoteModels.BaseRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.DownloadMangaRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaDetailsRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;
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

    public void getMangaDetails(final int id, final MangaDetailsCallback callback){
        getRetrofit().create(MyNetworkRoutes.class).getMangaDetails(id).enqueue(new Callback<BaseRemoteModel<MangaDetailsRemoteModel>>() {
            @Override
            public void onResponse(Call<BaseRemoteModel<MangaDetailsRemoteModel>> call, Response<BaseRemoteModel<MangaDetailsRemoteModel>> response) {
                callback.onDetailsSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<BaseRemoteModel<MangaDetailsRemoteModel>> call, Throwable t) {
                callback.onDetailsError(t, id);
            }
        });
    }

    public void prepareDownload(String ids, final DownloadCallback callback){
        getRetrofit().create(MyNetworkRoutes.class).getDownloadPages(ids).enqueue(new Callback<BaseRemoteModel<DownloadMangaRemoteModel>>() {
            @Override
            public void onResponse(Call<BaseRemoteModel<DownloadMangaRemoteModel>> call, Response<BaseRemoteModel<DownloadMangaRemoteModel>> response) {
                callback.onDownloadSucess(response.body().getData());
            }

            @Override
            public void onFailure(Call<BaseRemoteModel<DownloadMangaRemoteModel>> call, Throwable t) {
                callback.onDownloadError(t);
            }
        });
    }

    public ArrayList<Map<String,String>> getDownloadedChapters(int idManga){
        return getLocalData().query("SELECT m.description, c.name, c.webid FROM downloaded_chapters c INNER JOIN downloaded_mangas m ON m.webid = c.id_manga WHERE m.webid = " + idManga,null);
    }

    public boolean isDownloaded(int id){
        return getLocalData().checkIfThereIs("downloaded_chapters", "webid = " + id);
    }

    public interface MangaDetailsCallback{
        void onDetailsSuccess(MangaDetailsRemoteModel item);
        void onDetailsError(Throwable t, int idManga);
    }

    public interface DownloadCallback{
        void onDownloadSucess(DownloadMangaRemoteModel itens);
        void onDownloadError(Throwable t);
    }
}
