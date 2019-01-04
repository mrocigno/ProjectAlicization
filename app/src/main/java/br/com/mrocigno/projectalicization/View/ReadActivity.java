package br.com.mrocigno.projectalicization.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Adapters.PagesAdapter;
import br.com.mrocigno.projectalicization.Components.DaggerReadComponent;
import br.com.mrocigno.projectalicization.Config.MyActivity;
import br.com.mrocigno.projectalicization.Modules.ReadModule;
import br.com.mrocigno.projectalicization.Presenter.ReadInterface;
import br.com.mrocigno.projectalicization.Presenter.ReadPresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;

public class ReadActivity extends MyActivity implements ReadInterface {

    @Inject
    ReadPresenter presenter;

    RecyclerView rcyPages_Read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        rcyPages_Read = findViewById(R.id.rcyPages_Read);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rcyPages_Read);

        DaggerReadComponent.builder().readModule(new ReadModule(this)).build().inject(this);

        Intent intent = getIntent();
        if(intent.hasExtra("link")){
            presenter.loadData(intent.getStringExtra("link"));
        }else{
            finish();
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_read;
    }

    @Override
    public void changeActivity(Class mClass) {

    }

    @Override
    public void showPages(PagesDataRemoteModel pages) {
        rcyPages_Read.setLayoutManager(new LinearLayoutManager(ReadActivity.this, LinearLayoutManager.VERTICAL, false));
        rcyPages_Read.setAdapter(new PagesAdapter(ReadActivity.this, pages.getPages()));
        pages = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        System.gc();
    }

    @Override
    public void onBackPressed() {
        if(rcyPages_Read != null){
            ((PagesAdapter)rcyPages_Read.getAdapter()).setClear(true);
            rcyPages_Read.getAdapter().notifyDataSetChanged();
            rcyPages_Read.setAdapter(null);
            rcyPages_Read.setLayoutManager(null);
            rcyPages_Read = null;
        }
        super.onBackPressed();
    }

    @Override
    public boolean showToolbar() {
        return false;
    }
}
