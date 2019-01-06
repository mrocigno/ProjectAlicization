package br.com.mrocigno.projectalicization.View;


import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.Adapters.MangaThumbAdapter;
import br.com.mrocigno.projectalicization.Helpers.CustomGridLayoutManager;
import br.com.mrocigno.projectalicization.Presenter.DownloadPageInterface;
import br.com.mrocigno.projectalicization.Presenter.DownloadPagePresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadPageFragment extends Fragment implements DownloadPageInterface, MangaThumbAdapter.ActionsInterface {

    RecyclerView rcyDownloads_Download;
    DownloadPagePresenter presenter;

    public DownloadPageFragment newInstance(DownloadPagePresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);
        return this;
    }

    public DownloadPageFragment() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_download_page, container, false);
            rcyDownloads_Download = view.findViewById(R.id.rcyDownloads_Download);

            ArrayList<MangaListRemoteModel> list = new ArrayList<>();
            list.add(new MangaListRemoteModel("TESTE", "TESTE","https://upload.wikimedia.org/wikipedia/en/thumb/6/63/IMG_%28business%29.svg/1200px-IMG_%28business%29.svg.png", "dasd", 0));
            list.add(new MangaListRemoteModel("TESTE", "TESTE","https://upload.wikimedia.org/wikipedia/en/thumb/6/63/IMG_%28business%29.svg/1200px-IMG_%28business%29.svg.png", "dasd", 0));
            list.add(new MangaListRemoteModel("TESTE", "TESTE","https://upload.wikimedia.org/wikipedia/en/thumb/6/63/IMG_%28business%29.svg/1200px-IMG_%28business%29.svg.png", "dasd", 0));
            list.add(new MangaListRemoteModel("TESTE", "TESTE","https://upload.wikimedia.org/wikipedia/en/thumb/6/63/IMG_%28business%29.svg/1200px-IMG_%28business%29.svg.png", "dasd", 0));
            list.add(new MangaListRemoteModel("TESTE", "TESTE","https://upload.wikimedia.org/wikipedia/en/thumb/6/63/IMG_%28business%29.svg/1200px-IMG_%28business%29.svg.png", "dasd", 0));
            list.add(new MangaListRemoteModel("TESTE", "TESTE","https://upload.wikimedia.org/wikipedia/en/thumb/6/63/IMG_%28business%29.svg/1200px-IMG_%28business%29.svg.png", "dasd", 0));
            list.add(new MangaListRemoteModel("TESTE", "TESTE","https://upload.wikimedia.org/wikipedia/en/thumb/6/63/IMG_%28business%29.svg/1200px-IMG_%28business%29.svg.png", "dasd", 0));
            list.add(new MangaListRemoteModel("TESTE", "TESTE","https://upload.wikimedia.org/wikipedia/en/thumb/6/63/IMG_%28business%29.svg/1200px-IMG_%28business%29.svg.png", "dasd", 0));

            MangaThumbAdapter adapter = new MangaThumbAdapter(list, getActivity(), this);
            CustomGridLayoutManager lm = new CustomGridLayoutManager(getActivity(), getResources().getDimensionPixelOffset(R.dimen.thumb_width), LinearLayoutManager.VERTICAL, false);
            rcyDownloads_Download.setLayoutManager(lm);
            rcyDownloads_Download.setAdapter(adapter);

            if(presenter != null)
                presenter.loadData();
        }

        return view;
    }

    @Override
    public void onClickThumb(ActivityOptions options, MangaListRemoteModel item) {

    }

    @Override
    public void onClickSaveButton(MangaListRemoteModel item, boolean save) {

    }

    @Override
    public boolean verifieIfSaved(MangaListRemoteModel item) {
        return false;
    }

}
