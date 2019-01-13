package br.com.mrocigno.projectalicization.Presenter;

import android.Manifest;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

import br.com.mrocigno.projectalicization.Model.DetailsModel;
import br.com.mrocigno.projectalicization.RemoteModels.ChapterMangaRemoteModel;
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

    public boolean verifieIfIsDownloaded(int id){
        return model.isDownloaded(id);
    }

    @Override
    public void onDetailsSuccess(MangaDetailsRemoteModel item) {
        view.setProgressbar(false);
        view.setDetailsData(item);
        Log.e("TESTEEE", "onDetailsSuccess: ");
    }

    @Override
    public void onDetailsError(Throwable t, int idManga) {
        view.setProgressbar(false);
        ArrayList<Map<String, String>> list = model.getDownloadedChapters(idManga);

        if(list.size() > 0){
            ArrayList<ChapterMangaRemoteModel> chapters = new ArrayList<>();
            String description = "";

            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                chapters.add(new ChapterMangaRemoteModel(
                        Integer.parseInt(map.get("webid")),
                        idManga,
                        0,
                        map.get("name"),
                        "",
                        "",
                        false
                        ));
                description = map.get("description");
            }
            MangaDetailsRemoteModel item = new MangaDetailsRemoteModel(idManga, description, chapters, true);
            view.setDetailsData(item);
        }
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
