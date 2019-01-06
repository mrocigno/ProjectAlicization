package br.com.mrocigno.projectalicization.Presenter;

import android.widget.Toast;

import br.com.mrocigno.projectalicization.Model.DownloadPageModel;

public class DownloadPagePresenter implements DownloadPageInterface.Presenter {

    DownloadPageInterface.View view;
    DownloadPageModel model;
    MainInterface aView;

    public DownloadPagePresenter(DownloadPageInterface.View view, DownloadPageModel model, MainInterface aView) {
        this.view = view;
        this.model = model;
        this.aView = aView;
        setUpListener();
    }

    @Override
    public void setUpListener() {
        view.setPresenter(this);
    }

    @Override
    public void loadData() {
        Toast.makeText(view.getActivity(), "TS", Toast.LENGTH_LONG).show();
    }
}
