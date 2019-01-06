package br.com.mrocigno.projectalicization.View;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Adapters.MangaThumbAdapter;
import br.com.mrocigno.projectalicization.Adapters.SavedMangaAdapter;
import br.com.mrocigno.projectalicization.Adapters.SearchMangaAdapter;
import br.com.mrocigno.projectalicization.Config.MyFragment;
import br.com.mrocigno.projectalicization.Helpers.CustomGridLayoutManager;
import br.com.mrocigno.projectalicization.Model.FirstPageModel;
import br.com.mrocigno.projectalicization.Presenter.FirstPageInterface;
import br.com.mrocigno.projectalicization.Presenter.FirstPagePresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPageFragment extends MyFragment<FirstPageInterface.Presenter> implements FirstPageInterface.View, MangaThumbAdapter.ActionsInterface, SearchMangaAdapter.ActionsInterface {

    RecyclerView rcyMangas_FPage;
    RecyclerView rcySavedMangas_FPage;

    CardView cardSaved_FPage;

    public FirstPageFragment() {
        // Required empty public constructor
    }

    public static FirstPageFragment newInstance() {
        FirstPageFragment fragment = new FirstPageFragment();
        return fragment;
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_first_page, container, false);
            initVars();
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initVars(){
        rcyMangas_FPage = view.findViewById(R.id.rcyMangas_FPage);
        rcySavedMangas_FPage = view.findViewById(R.id.rcySavedMangas_FPage);
        cardSaved_FPage = view.findViewById(R.id.cardSaved_FPage);
    }

    @Override
    public void addList(ArrayList<MangaListRemoteModel> response) {
        MangaThumbAdapter mangaThumbAdapter = new MangaThumbAdapter(response, getActivity(), this);
        CustomGridLayoutManager glm = new CustomGridLayoutManager(getActivity(), getResources().getDimensionPixelOffset(R.dimen.thumb_width), GridLayoutManager.VERTICAL, false);
        rcyMangas_FPage.setLayoutManager(glm);
        rcyMangas_FPage.setAdapter(mangaThumbAdapter);
    }


    @Override
    public void addListSaves(ArrayList<Map<String, String>> itens) {
        if(itens.size() > 0) {
            cardSaved_FPage.setVisibility(View.VISIBLE);
            SavedMangaAdapter savedMangaAdapter = new SavedMangaAdapter(getActivity(), itens);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            rcySavedMangas_FPage.setLayoutManager(llm);
            rcySavedMangas_FPage.setAdapter(savedMangaAdapter);
        }else{
            cardSaved_FPage.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClickSaveButton(MangaListRemoteModel item, boolean save) {
        presenter.saveManga(item, save);
    }

    @Override
    public boolean verifieIfSaved(MangaListRemoteModel item) {
        return presenter.checkSaved(item);
    }

    @Override
    public Activity getActivityThumb() {
        return getActivity();
    }

    @Override
    public Activity getActivitySearch() {
        return getActivity();
    }
}
