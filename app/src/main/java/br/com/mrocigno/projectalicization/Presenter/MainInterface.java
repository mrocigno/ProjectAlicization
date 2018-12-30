package br.com.mrocigno.projectalicization.Presenter;

import java.util.ArrayList;
import java.util.Map;

import br.com.mrocigno.projectalicization.RemoteModels.BaseArrayDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public interface MainInterface {
    void addList(BaseArrayDataRemoteModel<MangaListRemoteModel> response);
    void addListSaves(ArrayList<Map<String, String>> itens);
    void setProgressbar(boolean visible);
}
