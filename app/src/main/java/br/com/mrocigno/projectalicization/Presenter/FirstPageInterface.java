package br.com.mrocigno.projectalicization.Presenter;

import java.util.ArrayList;
import java.util.Map;

import br.com.mrocigno.projectalicization.Config.MyPresenter;
import br.com.mrocigno.projectalicization.Config.MyView;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public interface FirstPageInterface {

    interface View extends MyView<FirstPageInterface.Presenter>{
        void addList(ArrayList<MangaListRemoteModel> response);
        void addListSaves(ArrayList<Map<String, String>> itens);
    }

    interface Presenter extends MyPresenter {
        void loadData();
        void getSavedMangas();
        void saveManga(MangaListRemoteModel item, boolean save);
        boolean checkSaved(MangaListRemoteModel item);
    }
}
