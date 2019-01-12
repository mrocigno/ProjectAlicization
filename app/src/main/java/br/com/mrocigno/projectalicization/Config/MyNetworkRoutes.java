package br.com.mrocigno.projectalicization.Config;

import br.com.mrocigno.projectalicization.RemoteModels.BaseArrayDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.BaseRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.DownloadMangaRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaDetailsRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyNetworkRoutes {

    @GET("list.php")
    Call<BaseArrayDataRemoteModel<MangaListRemoteModel>> getListMangas(@Query("page") String page, @Query("limit") String limit);

    @GET("search.php")
    Call<BaseArrayDataRemoteModel<MangaListRemoteModel>> searchMangas(@Query("search") String search);

    @GET("manga.php")
    Call<BaseRemoteModel<MangaDetailsRemoteModel>> getMangaDetails(@Query("id") int id);

    @GET("pages.php")
    Call<BaseRemoteModel<PagesDataRemoteModel>> getPagesData(@Query("id") int id);

    @GET("pageJPG.php")
    Call<ResponseBody> getPageJPG(@Query("id") int id);

    @GET("download.php")
    Call<BaseRemoteModel<DownloadMangaRemoteModel>> getDownloadPages(@Query("chapters") String chapters);
}
