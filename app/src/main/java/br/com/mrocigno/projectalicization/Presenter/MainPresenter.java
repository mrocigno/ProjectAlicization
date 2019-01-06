package br.com.mrocigno.projectalicization.Presenter;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.Model.MainModel;
import br.com.mrocigno.projectalicization.RemoteModels.BaseArrayDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public class MainPresenter implements MainModel.MangaSearchCallback{

    MainModel model;
    MainInterface view;

    public MainPresenter(MainInterface view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void searchManga(String name){
        view.setProgressbar(true);
        model.searchManga(name, this);
    }

    public boolean checkSaved(MangaListRemoteModel item){
        return model.checkIfIsSaved(item);
    }

    @Override
    public void onSearchSuccess(ArrayList<MangaListRemoteModel> response) {
        view.setProgressbar(false);
        view.addSearchList(response);
    }

    @Override
    public void onSearchError(Throwable t) {
        view.setProgressbar(false);
    }
}
