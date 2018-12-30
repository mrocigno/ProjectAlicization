package br.com.mrocigno.projectalicization.Presenter;

import android.util.Log;

import br.com.mrocigno.projectalicization.Model.MainModel;
import br.com.mrocigno.projectalicization.RemoteModels.BaseArrayDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.ListRemoteModels;

public class MainPresenter implements MainModel.MangaListCallback {

    MainModel model;

    MainInterface view;

    public MainPresenter(MainInterface view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void loadData(){
        view.setProgressbar(true);
        model.getMangaList("4","20", this);
        view.addListSaves(model.getSavedMangaList());
    }

    public void saveManga(ListRemoteModels item, boolean save){
        model.saveManga(item,save);
        view.addListSaves(model.getSavedMangaList());
    }

    @Override
    public void onSuccess(BaseArrayDataRemoteModel<ListRemoteModels> response) {
        view.addList(response);
        view.setProgressbar(false);
    }

    @Override
    public void onError(Throwable t) {
        view.setProgressbar(false);
    }
}
