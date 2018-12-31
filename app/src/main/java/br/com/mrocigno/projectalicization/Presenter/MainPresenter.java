package br.com.mrocigno.projectalicization.Presenter;

import br.com.mrocigno.projectalicization.Model.MainModel;
import br.com.mrocigno.projectalicization.RemoteModels.BaseArrayDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public class MainPresenter implements MainModel.MangaListCallback, MainModel.MangaSearchCallback{

    MainModel model;
    MainInterface view;

    public MainPresenter(MainInterface view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void loadData(){
        view.setProgressbar(true);
        model.getMangaList("20","20", this);
        view.addListSaves(model.getSavedMangaList());
    }

    public void searchManga(String name){
        view.setProgressbar(true);
        model.searchManga(name, this);
    }

    public void saveManga(MangaListRemoteModel item, boolean save){
        model.saveManga(item,save);
        view.addListSaves(model.getSavedMangaList());
    }

    @Override
    public void onSuccess(BaseArrayDataRemoteModel<MangaListRemoteModel> response) {
        view.addList(response);
        view.setProgressbar(false);
    }

    @Override
    public void onError(Throwable t) {
        view.setProgressbar(false);
    }

    @Override
    public void onSearchSuccess(BaseArrayDataRemoteModel<MangaListRemoteModel> response) {
        view.setProgressbar(false);
        view.addSearchList(response);
    }

    @Override
    public void onSearchError(Throwable t) {
        view.setProgressbar(false);
    }
}
