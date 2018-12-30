package br.com.mrocigno.projectalicization.Presenter;

import android.content.Intent;

import br.com.mrocigno.projectalicization.Model.DetailsModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public class DetailsPresenter {

    DetailsInterface view;
    DetailsModel model;

    public DetailsPresenter(DetailsInterface view, DetailsModel model) {
        this.view = view;
        this.model = model;
    }

    public void loadData(Intent intent){
        MangaListRemoteModel item = (MangaListRemoteModel) intent.getSerializableExtra("manga");
        view.addImediateData(item);
    }

}
