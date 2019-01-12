package br.com.mrocigno.projectalicization.Model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Map;

import br.com.mrocigno.projectalicization.Config.MyModel;

public class DownloadPageModel extends MyModel {

    public DownloadPageModel(Activity activity) {
        super(activity);
    }

    public ArrayList<Map<String, String>> getDownloadedMangas(){
        return getLocalData().query("SELECT * FROM downloaded_mangas", null);
    }

}
