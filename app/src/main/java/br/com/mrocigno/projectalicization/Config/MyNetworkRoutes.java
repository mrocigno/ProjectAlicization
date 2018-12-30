package br.com.mrocigno.projectalicization.Config;

import br.com.mrocigno.projectalicization.RemoteModels.BaseArrayDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.ListRemoteModels;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyNetworkRoutes {

    @GET("search.php")
    Call<BaseArrayDataRemoteModel<ListRemoteModels>> getListMangas(@Query("page") String page, @Query("limit") String limit);

}
