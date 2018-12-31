package br.com.mrocigno.projectalicization.Presenter;

import br.com.mrocigno.projectalicization.Config.MyView;
import br.com.mrocigno.projectalicization.RemoteModels.MangaDetailsRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public interface DetailsInterface extends MyView {
    void addImediateData(MangaListRemoteModel item);
    void setSaved(boolean saved);
    void setDetailsData(MangaDetailsRemoteModel item);
    void setProgressbar(boolean visible);
}
