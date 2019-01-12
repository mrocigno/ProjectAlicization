package br.com.mrocigno.projectalicization.Presenter;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.Config.MyView;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public interface DownloadPageInterface extends MyView {
    void addDownloadedMangasList(ArrayList<MangaListRemoteModel> list);
}
