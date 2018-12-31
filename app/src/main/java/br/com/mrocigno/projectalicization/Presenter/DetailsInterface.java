package br.com.mrocigno.projectalicization.Presenter;

import br.com.mrocigno.projectalicization.Config.MyView;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public interface DetailsInterface extends MyView {
    void addImediateData(MangaListRemoteModel item);
}
