package br.com.mrocigno.projectalicization.Presenter;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Model.FirstPageModel;
import br.com.mrocigno.projectalicization.Model.MainModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.View.MainActivity;

public class FirstPagePresenter implements FirstPageModel.MangaListCallback, FirstPageInterface.Presenter{

    FirstPageModel model;
    FirstPageInterface.View view;
    MainInterface aView;

    FirstPageModel teste;

    public FirstPagePresenter(FirstPageInterface.View view, FirstPageModel model, MainInterface aView) {
        this.view = view;
        this.model = model;
        this.aView = aView;
        setUpListener();
    }

    @Override
    public void loadData(){
        aView.setProgressbar(true);
        model.getMangaList("20","20", this);
        getSavedMangas();
    }

    @Override
    public void getSavedMangas() {
        view.addListSaves(model.getSavedMangaList());
    }

    @Override
    public void saveManga(MangaListRemoteModel item, boolean save){
        model.saveManga(item,save);
        getSavedMangas();
    }

    @Override
    public boolean checkSaved(MangaListRemoteModel item){
        return model.checkIfIsSaved(item);
    }

    @Override
    public void onSuccess(ArrayList<MangaListRemoteModel> response) {
        view.addList(response);
        aView.setProgressbar(false);
    }

    @Override
    public void onError(Throwable t) {
        aView.setProgressbar(false);
    }

    @Override
    public void setUpListener() {
        view.setPresenter(this);
    }
}
