package br.com.mrocigno.projectalicization.View;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Adapters.MangaThumbAdapter;
import br.com.mrocigno.projectalicization.Adapters.SavedMangaAdapter;
import br.com.mrocigno.projectalicization.Components.DaggerMainComponent;
import br.com.mrocigno.projectalicization.Config.MyActivity;
import br.com.mrocigno.projectalicization.Modules.MainModule;
import br.com.mrocigno.projectalicization.Presenter.MainInterface;
import br.com.mrocigno.projectalicization.Presenter.MainPresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.BaseArrayDataRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.ListRemoteModels;

public class MainActivity extends MyActivity implements MainInterface, MangaThumbAdapter.ActionsInterface {

    @Inject
    MainPresenter presenter;

    RecyclerView rcyMangas_Main;
    RecyclerView rcySavedMangas_Main;

    CardView cardSaved_Main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // inside your activity (if you did not enable transitions in your theme)
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        // set an exit transition
//        getWindow().setExitTransition(new Explode());
//        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());
//        getWindow().setSharedElementExitTransition(new ChangeImageTransform());
        super.onCreate(savedInstanceState);

        initDagger();
        initVars();

        presenter.loadData();
    }

    private void initVars(){
        rcyMangas_Main = findViewById(R.id.rcyMangas_Main);
        rcySavedMangas_Main = findViewById(R.id.rcySavedMangas_Main);
        cardSaved_Main = findViewById(R.id.cardSaved_Main);
    }

    private void initDagger() {
        DaggerMainComponent.builder().mainModule(new MainModule(this, getActivity())).build().inject(this);
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
    public void addList(BaseArrayDataRemoteModel<ListRemoteModels> response) {
        MangaThumbAdapter mangaThumbAdapter = new MangaThumbAdapter(response.getData(), getActivity(), this);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2 , GridLayoutManager.VERTICAL, false);
        rcyMangas_Main.setLayoutManager(glm);
        rcyMangas_Main.setAdapter(mangaThumbAdapter);
    }

    @Override
    public void addListSaves(ArrayList<Map<String, String>> itens) {
        if(itens.size() > 0) {
            cardSaved_Main.setVisibility(View.VISIBLE);
            SavedMangaAdapter savedMangaAdapter = new SavedMangaAdapter(getActivity(), itens);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            rcySavedMangas_Main.setLayoutManager(llm);
            rcySavedMangas_Main.setAdapter(savedMangaAdapter);
        }else{
            cardSaved_Main.setVisibility(View.GONE);
        }
    }

    @Override
    public void setProgressbar(boolean visible) {
        setProgressbarVisible(visible);
    }

    @Override
    public void onClickSaveButton(ListRemoteModels item, boolean save) {
        presenter.saveManga(item, save);
    }

    @Override
    public void onCellClick(ListRemoteModels item) {
//        changeActivity();
    }

    @Override
    public Activity getActivity() {
        return super.getActivity();
    }
}
