package br.com.mrocigno.projectalicization.Presenter;

import java.util.Timer;
import java.util.TimerTask;

import br.com.mrocigno.projectalicization.View.MainActivity;

public class SplashPresenter {

    SplashInterface view;

    public SplashPresenter(SplashInterface view) {
        this.view = view;
    }

    public void loadData(){
        //If has something to load, load here
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                view.changeActivity(MainActivity.class);
            }
        }, 2000);
    }
}
