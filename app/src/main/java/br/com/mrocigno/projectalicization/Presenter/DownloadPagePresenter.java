package br.com.mrocigno.projectalicization.Presenter;

import android.widget.Toast;

import br.com.mrocigno.projectalicization.Model.DownloadPageModel;

public class DownloadPagePresenter {

    DownloadPageInterface view;
    DownloadPageModel model;
    MainInterface aView;

    public void setView(DownloadPageInterface view) {
        this.view = view;
    }

    public DownloadPagePresenter(DownloadPageModel model, MainInterface aView) {
        this.model = model;
        this.aView = aView;
    }

    public void loadData() {
        Toast.makeText(view.getActivity(), "TS", Toast.LENGTH_LONG).show();
    }
}
