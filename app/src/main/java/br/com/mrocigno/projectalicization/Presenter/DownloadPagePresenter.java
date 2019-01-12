package br.com.mrocigno.projectalicization.Presenter;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import br.com.mrocigno.projectalicization.Model.DownloadPageModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

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
        ArrayList<Map<String, String>> data = model.getDownloadedMangas();
        if(data.size() > 0){
            ArrayList<MangaListRemoteModel> list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                Map<String, String> map = data.get(i);
                list.add(new MangaListRemoteModel(map.get("name"), map.get("link"), map.get("cover"), "", false, Integer.parseInt(map.get("webid")), 0));
            }
            view.addDownloadedMangasList(list);
        }
    }
}
