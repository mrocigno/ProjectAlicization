package br.com.mrocigno.projectalicization.Services;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import br.com.mrocigno.projectalicization.Database.LocalData;
import br.com.mrocigno.projectalicization.Modules.DataModule;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.ChapterMangaRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.DownloadMangaRemoteModel;
import br.com.mrocigno.projectalicization.Utils.DialogUtil;

public class DownloadServiceTest extends Service {

    NotificationCompat.Builder mBuilder;
    NotificationManagerCompat notificationManager;

    int MAX = 0;
    int PROGRESS = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.hasExtra("data")){
            DownloadMangaRemoteModel data = (DownloadMangaRemoteModel) intent.getSerializableExtra("data");

            for (int i = 0; i < data.getChapters().size(); i++) {
                MAX += data.getChapters().get(i).getPages().size();
            }

            mBuilder = new NotificationCompat.Builder(this, "download")
                    .setSmallIcon(android.R.drawable.stat_sys_download)
                    .setContentTitle("Baixando")
                    .setContentText(data.getName())
                    .setProgress(MAX,0, false)
                    .setPriority(NotificationCompat.PRIORITY_MAX);

            notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, mBuilder.build());

            loop(data);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void loop(final DownloadMangaRemoteModel item) {
        LocalData data = new LocalData(getBaseContext());

        ContentValues cvManga = new ContentValues();
        cvManga.put("webid", item.getId());
        cvManga.put("link", item.getLink());
        cvManga.put("cover", item.getCover());
        cvManga.put("cover", item.getCover());
        data.insert("downloaded_mangas", cvManga);

        ArrayList<DownloadMangaRemoteModel.Chapters> chapters = item.getChapters();
        for (int i = 0; i < chapters.size(); i++) {
            DownloadMangaRemoteModel.Chapters chapter = chapters.get(i);
            ContentValues cvChapter = new ContentValues();
            cvChapter.put("name", chapter.getName_chapter());
            cvChapter.put("link", chapter.getLink_chapter());
            cvChapter.put("id_manga", item.getId());
            cvChapter.put("webid", chapter.getId());
            data.insert("downloaded_chapters", cvChapter);

            ArrayList<DownloadMangaRemoteModel.Chapters.Pages> pages = chapter.getPages();
            for (int j = 0; j < pages.size(); j++) {
                DownloadMangaRemoteModel.Chapters.Pages page = pages.get(j);

                String path = new ContextWrapper(getApplicationContext()).getDir("imageDir", Context.MODE_PRIVATE).toString();
                String name = chapter.getId() + "-" + page.getNum_page() + ".jpg";
                String localFile = path + "/" + name;

                ContentValues cvPages = new ContentValues();
                cvPages.put("webid", page.getId());
                cvPages.put("id_chapter", chapter.getId());
                cvPages.put("link_page", page.getLink_page());
                cvPages.put("local_path", localFile);
                cvPages.put("num_page", page.getNum_page());
                data.insert("downloaded_pages", cvPages);

                download(path, name,DataModule.BASE_URL + "pageJPG.php?id=" + page.getId());
            }
        }
    }

    public void download(final String savePath, final String fileName, final String url){
        AndroidNetworking.download(url, savePath, fileName)
                .build()
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        mBuilder.setProgress(MAX,++PROGRESS, false);
                        notificationManager.notify(1, mBuilder.build());
                        if(MAX == PROGRESS){
                            Log.e("TESTEEE", "fim");
                            mBuilder.setProgress(0, 0, false);
                            mBuilder.setSmallIcon(R.drawable.ic_done);
                            mBuilder.setContentTitle("Download completo");
                            notificationManager.notify(1, mBuilder.build());
                            DownloadServiceTest.this.stopSelf();
                        }else{
                            Log.e("TESTEEE", "MAX = " + MAX + " PROGRESS = " + PROGRESS);
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        download(savePath, fileName, url);
                        Log.e("TESTEEE", "erro");
                    }
                });
    }
}
