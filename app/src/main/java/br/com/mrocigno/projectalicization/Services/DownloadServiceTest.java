package br.com.mrocigno.projectalicization.Services;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import br.com.mrocigno.projectalicization.Database.LocalData;
import br.com.mrocigno.projectalicization.RemoteModels.ChapterMangaRemoteModel;
import br.com.mrocigno.projectalicization.Utils.DialogUtil;

public class DownloadServiceTest extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void loop(final ChapterMangaRemoteModel item) {
        LocalData data = new LocalData(getBaseContext());
        Target target = new Target() {

            @Override
            public void onPrepareLoad(Drawable arg0) {
                return;
            }

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom arg1) {

                try {
                    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + "teste.jpg");

                    file.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                    ostream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                return;
            }

        };

        Picasso.get().load("http://blog.concretesolutions.com.br/wp-content/uploads/2015/04/Android1.png").into(target);
    }
}
