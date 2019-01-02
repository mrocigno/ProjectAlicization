package br.com.mrocigno.projectalicization.Presenter;

import br.com.mrocigno.projectalicization.Config.MyView;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;

public interface ReadInterface extends MyView {
    void showPages(PagesDataRemoteModel pages);
    void setLoadProgress(boolean visible);
    void setMsgProgress(String string);
}
