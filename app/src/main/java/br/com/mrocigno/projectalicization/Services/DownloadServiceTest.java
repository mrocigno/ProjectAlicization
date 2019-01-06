package br.com.mrocigno.projectalicization.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import br.com.mrocigno.projectalicization.Utils.DialogUtil;

public class DownloadServiceTest extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        loop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        loop();
        return null;
    }

    public void loop(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e("TESTEEE", "run: ");
                DialogUtil.inflateLayoutDialog(getBaseContext(), "Mensgem top", "btn ok", true, null);
            }
        }, 5000);
    }
}
