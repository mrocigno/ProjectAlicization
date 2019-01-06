package br.com.mrocigno.projectalicization.Presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import br.com.mrocigno.projectalicization.Model.DetailsModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaDetailsRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public class DetailsPresenter implements DetailsModel.MangaDetailsCallback {

    DetailsInterface view;
    DetailsModel model;

    public DetailsPresenter(DetailsInterface view, DetailsModel model) {
        this.view = view;
        this.model = model;
    }

    public void loadData(MangaListRemoteModel item){
        view.setProgressbar(true);
        view.addImediateData(item);
        view.setSaved(model.isSaved(item.getWebid()));
        model.getMangaDetails(item.getLink(), this);
    }

    public void saveManga(MangaListRemoteModel item, boolean save){
        model.saveManga(item, save);
        view.setSaved(save);
    }

    @Override
    public void onDetailsSuccess(MangaDetailsRemoteModel item) {
        view.setProgressbar(false);
        view.setDetailsData(item);
        Log.e("TESTEEE", "onDetailsSuccess: ");
    }

    @Override
    public void onDetailsError(Throwable t) {
        view.setProgressbar(false);
        Log.e("TESTEEE", "onDetailsError: ", t);
    }
}
