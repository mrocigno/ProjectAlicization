package br.com.mrocigno.projectalicization.Presenter;

import br.com.mrocigno.projectalicization.Config.MyPresenter;
import br.com.mrocigno.projectalicization.Config.MyView;

public interface DownloadPageInterface {
    interface View extends MyView<DownloadPageInterface.Presenter> {

    }
    interface Presenter extends MyPresenter {
        void loadData();
    }
}
