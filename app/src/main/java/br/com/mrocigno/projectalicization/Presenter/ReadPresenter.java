package br.com.mrocigno.projectalicization.Presenter;

import br.com.mrocigno.projectalicization.Model.ReadModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;

public class ReadPresenter implements ReadModel.DataCallback {

    ReadInterface view;
    ReadModel model;

    public ReadPresenter(ReadInterface view, ReadModel model) {
        this.view = view;
        this.model = model;
    }

    public void loadData(int id){
        PagesDataRemoteModel pages = model.loadLocalData(id);
        if(pages != null){
            onDataSuccess(pages);
        }else{
            model.loadData(id, this);
        }
    }

    @Override
    public void onDataSuccess(PagesDataRemoteModel data) {
        view.showPages(data);
        data = null;
    }

    @Override
    public void onDataError(Throwable t) {

    }

}
