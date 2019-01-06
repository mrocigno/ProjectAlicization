package br.com.mrocigno.projectalicization.Presenter;

import java.util.ArrayList;
import java.util.Map;

import br.com.mrocigno.projectalicization.Config.MyView;
import br.com.mrocigno.projectalicization.RemoteModels.BaseArrayDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public interface MainInterface extends MyView {
    void addSearchList(ArrayList<MangaListRemoteModel> response);
    void setProgressbar(boolean visible);
}
