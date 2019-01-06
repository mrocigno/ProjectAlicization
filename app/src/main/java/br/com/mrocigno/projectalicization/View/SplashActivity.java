package br.com.mrocigno.projectalicization.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Components.DaggerSplashComponent;
import br.com.mrocigno.projectalicization.Presenter.SplashInterface;
import br.com.mrocigno.projectalicization.Modules.SplashModule;
import br.com.mrocigno.projectalicization.Presenter.SplashPresenter;

public class SplashActivity extends AppCompatActivity implements SplashInterface {

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();

        presenter.loadData();
    }

    private void initDagger() {
        DaggerSplashComponent
                .builder()
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void changeActivity(Class mClass) {
        startActivity(new Intent(SplashActivity.this, mClass));
        finish();
    }

    @Override
    public Activity getActivity() {
        return null;
    }

}
