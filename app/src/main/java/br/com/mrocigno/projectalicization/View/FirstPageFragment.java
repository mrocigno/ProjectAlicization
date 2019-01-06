package br.com.mrocigno.projectalicization.View;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
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
import br.com.mrocigno.projectalicization.Helpers.CustomGridLayoutManager;
import br.com.mrocigno.projectalicization.Model.FirstPageModel;
import br.com.mrocigno.projectalicization.Presenter.FirstPageInterface;
import br.com.mrocigno.projectalicization.Presenter.FirstPagePresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.Services.DownloadServiceTest;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPageFragment extends Fragment implements FirstPageInterface, MangaThumbAdapter.ActionsInterface {

    RecyclerView rcyMangas_FPage;
    RecyclerView rcySavedMangas_FPage;
    FirstPagePresenter presenter;

    CardView cardSaved_FPage;

    public FirstPageFragment() {
        // Required empty public constructor
    }

    public FirstPageFragment newInstance(FirstPagePresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
        return this;
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_first_page, container, false);
            initVars();

            if(presenter != null)
                presenter.loadData();
        }

        return view;
    }

    private void initVars(){
        rcyMangas_FPage = view.findViewById(R.id.rcyMangas_FPage);
        rcySavedMangas_FPage = view.findViewById(R.id.rcySavedMangas_FPage);
        cardSaved_FPage = view.findViewById(R.id.cardSaved_FPage);
    }

    @Override
    public void addList(ArrayList<MangaListRemoteModel> response) {
        if(isAdded()){
            MangaThumbAdapter mangaThumbAdapter = new MangaThumbAdapter(response, getActivity(), this);
            CustomGridLayoutManager glm = new CustomGridLayoutManager(getActivity(), getResources().getDimensionPixelOffset(R.dimen.thumb_width), GridLayoutManager.VERTICAL, false);
            rcyMangas_FPage.setLayoutManager(glm);
            rcyMangas_FPage.setAdapter(mangaThumbAdapter);
        }
    }


    @Override
    public void addListSaves(ArrayList<Map<String, String>> itens) {
        if(isAdded()){
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
    }

    @Override
    public void onClickThumb(ActivityOptions options, MangaListRemoteModel item) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("manga", item);
        getActivity().startActivity(intent, options.toBundle());
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
    public void onResume() {
        super.onResume();

    }
}
