package br.com.mrocigno.projectalicization.Presenter;

import br.com.mrocigno.projectalicization.Model.ReadModel;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;

public class ReadPresenter implements ReadModel.DataCallback {

    ReadInterface view;
    ReadModel model;

    public ReadPresenter(ReadInterface view, ReadModel model) {
        this.view = view;
        this.model = model;
    }

    public void loadData(String link){
        view.setLoadProgress(true);
        view.setMsgProgress("Carregando...");
        model.loadData(link, this);
    }

    @Override
    public void onDataSuccess(PagesDataRemoteModel data) {
        view.setLoadProgress(false);
        view.showPages(data);
    }

    @Override
    public void onDataError(Throwable t) {
        view.setMsgProgress("Houve um erro e o PDF não pôde ser criado");
    }

}
