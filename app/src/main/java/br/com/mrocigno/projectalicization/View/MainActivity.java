package br.com.mrocigno.projectalicization.View;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Adapters.SearchMangaAdapter;
import br.com.mrocigno.projectalicization.Adapters.ViewPagerAdapter;
import br.com.mrocigno.projectalicization.Components.DaggerMainComponent;
import br.com.mrocigno.projectalicization.Config.MyActivity;
import br.com.mrocigno.projectalicization.Modules.MainModule;
import br.com.mrocigno.projectalicization.Presenter.DownloadPagePresenter;
import br.com.mrocigno.projectalicization.Presenter.FirstPagePresenter;
import br.com.mrocigno.projectalicization.Presenter.MainInterface;
import br.com.mrocigno.projectalicization.Presenter.MainPresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

public class MainActivity extends MyActivity implements MainInterface, SearchMangaAdapter.ActionsInterface {

    @Inject
    MainPresenter presenter;

    @Inject FirstPageFragment firstPageFragment;
    @Inject DownloadPageFragment downloadPageFragment;

    RecyclerView rcySearch_Main;

    FrameLayout frmBlack_Main;

    BottomNavigationView bnvMain_Main;

    boolean showSearchLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        initVars();
        removeFragmentToActivity(getSupportFragmentManager());
        addFragmentToActivity(getSupportFragmentManager(), firstPageFragment, R.id.frmContainer_Main, "pg1");
    }

    private void initVars(){
        initDagger();

        rcySearch_Main = findViewById(R.id.rcySearch_Main);

        bnvMain_Main = findViewById(R.id.bnvMain_Main);
        bnvMain_Main.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:{
                        switchFragment(firstPageFragment, "pg1");
                        return true;
                    }
                    case R.id.navigation_download:{
                        switchFragment(downloadPageFragment, "pg2");
                        return true;
                    }
                }
                return false;
            }
        });

        frmBlack_Main = findViewById(R.id.frmBlack_Main);
        frmBlack_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSrvSearch_Default().setQuery("", false);
                getSrvSearch_Default().setIconified(true);
            }
        });
    }

    private void switchFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        if(manager.findFragmentByTag(tag) == null){
            addFragmentToActivity(manager, fragment, R.id.frmContainer_Main, tag);
        }else{
            showFragmentToActivity(manager, fragment);
        }
    }

    private void initDagger() {
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void changeActivity(Class mClass) {
        Intent intent = new Intent(getActivity(), mClass);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


    @Override
    public void addSearchList(ArrayList<MangaListRemoteModel> response) {
        if(!showSearchLoaded) return;
        SearchMangaAdapter searchMangaAdapter = new SearchMangaAdapter(getActivity(), response, this);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcySearch_Main.setLayoutManager(llm);
        rcySearch_Main.setAdapter(searchMangaAdapter);
    }

    @Override
    public void setProgressbar(boolean visible) {
        setProgressbarVisible(visible);
    }

    @Override
    public Activity getActivitySearch() {
        return super.getActivity();
    }

    @Override
    public void onSearchViewClick() {
        super.onSearchViewClick();
        frmBlack_Main.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSearchViewClose() {
        super.onSearchViewClose();
        frmBlack_Main.setVisibility(View.GONE);
    }

    @Override
    public void onSearchViewTextSubmit(String s) {
        presenter.searchManga(s);
    }

    @Override
    public void onSearchViewTextChange(String s) {
        if(s.equals("")){
            rcySearch_Main.setAdapter(null);
            showSearchLoaded = false;
            return;
        }else{
            showSearchLoaded = true;
        }
        if(s.length() > 3){
            presenter.searchManga(s);
        }
    }


}
