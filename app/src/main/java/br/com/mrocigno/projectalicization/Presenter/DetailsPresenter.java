package br.com.mrocigno.projectalicization.Presenter;

import android.Manifest;
import android.content.Intent;
import android.util.Log;

import br.com.mrocigno.projectalicization.Model.DetailsModel;
import br.com.mrocigno.projectalicization.RemoteModels.DownloadMangaRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaDetailsRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.Services.DownloadServiceTest;
import br.com.mrocigno.projectalicization.Utils.PermissionUtils;

public class DetailsPresenter implements DetailsModel.MangaDetailsCallback, DetailsModel.DownloadCallback, PermissionUtils.PermissionAskListener {

    public static final int REQUEST_WRITE_EXTERNAL = 10;

    DetailsInterface view;
    DetailsModel model;
    private String ids;

    public DetailsPresenter(DetailsInterface view, DetailsModel model) {
        this.view = view;
        this.model = model;
    }

    public void loadData(MangaListRemoteModel item){
        view.setProgressbar(true);
        view.addImediateData(item);
        view.setSaved(model.isSaved(item.getId()));
        model.getMangaDetails(item.getId(), this);
    }

    public void saveManga(MangaListRemoteModel item, boolean save){
        model.saveManga(item, save);
        view.setSaved(save);
    }

    public void prepareDownload(String ids){
        this.ids = ids;
        PermissionUtils.checkPermission(view.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE, this);
    }

    @Override
    public void onDetailsSuccess(MangaDetailsRemoteModel item) {
        view.setProgressbar(false);
        view.setDetailsData(item);
        Log.e("TESTEEE", "onDetailsSuccess: ");
    }

    @Override
    public void onDetailsError(Throwable t) {
        view.setProgressbar(false);
        Log.e("TESTEEE", "onDetailsError: ", t);
    }

    @Override
    public void onDownloadSucess(DownloadMangaRemoteModel itens) {
        Intent intent = new Intent(view.getActivity(), DownloadServiceTest.class);
        intent.putExtra("data", itens);
        view.getActivity().startService(intent);
    }

    @Override
    public void onDownloadError(Throwable t) {

    }

    @Override
    public void onFirstTimeAsked() {
        view.getActivity().requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL);
    }

    @Override
    public void onPermanentlyDenied() {
        Log.e("TESTEEE", "onPermanentlyDenied: ");
    }

    @Override
    public void onPermissionDisabled() {
        view.getActivity().requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL);
    }

    @Override
    public void onPermissionGranted() {
        model.prepareDownload(ids, this);
    }
}
