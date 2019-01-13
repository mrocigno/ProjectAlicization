package br.com.mrocigno.projectalicization.View;

import android.app.ActivityOptions;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Adapters.ChaptersAdapter;
import br.com.mrocigno.projectalicization.Components.DaggerDetailsComponent;
import br.com.mrocigno.projectalicization.Config.MyActivity;
import br.com.mrocigno.projectalicization.Modules.DetailsModule;
import br.com.mrocigno.projectalicization.Presenter.DetailsInterface;
import br.com.mrocigno.projectalicization.Presenter.DetailsPresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.ChapterMangaRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaDetailsRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.Services.DownloadServiceTest;
import br.com.mrocigno.projectalicization.Utils.GlideUtil;

public class DetailsActivity extends MyActivity implements DetailsInterface, ChaptersAdapter.ActionsChaptersAdapter {

    @Inject
    DetailsPresenter presenter;

    TextView txtTitle_Details;
    TextView txtDescription_Details;
    TextView txtYear_Details;
    TextView txtAlterName_Details;
    TextView txtArtist_Details;
    TextView txtGenre_Details;
    TextView txtStatus_Details;
    TextView txtAutor_Details;

    ImageView imgCover_Details;
    ImageView imgSave_Details;

    ProgressBar pgrBar_Details;

    RecyclerView rcyChapters_Details;

    MangaListRemoteModel item;

    CardView cardDetails_Details;

    Boolean isOffilne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        super.onCreate(savedInstanceState);

        initDagger();
        initVars();
        showSearchView(false);
        setScrollFlag();

        Intent intent = getIntent();
        if(intent.hasExtra("manga")){
            item = (MangaListRemoteModel) intent.getSerializableExtra("manga");
            presenter.loadData(item);
        }else{
            finish();
        }
    }

    private void initVars() {
        txtTitle_Details = findViewById(R.id.txtTitle_Details);
        txtDescription_Details = findViewById(R.id.txtDescription_Details);
        txtYear_Details = findViewById(R.id.txtYear_Details);
        txtAlterName_Details = findViewById(R.id.txtAlterName_Details);
        txtArtist_Details = findViewById(R.id.txtArtist_Details);
        txtGenre_Details = findViewById(R.id.txtGenre_Details);
        txtStatus_Details = findViewById(R.id.txtStatus_Details);
        txtAutor_Details = findViewById(R.id.txtAutor_Details);
        imgCover_Details = findViewById(R.id.imgCover_Details);
        imgSave_Details = findViewById(R.id.imgSave_Details);
        pgrBar_Details = findViewById(R.id.pgrBar_Details);
        rcyChapters_Details = findViewById(R.id.rcyChapters_Details);
        cardDetails_Details = findViewById(R.id.cardDetails_Details);
        showFab(true, getDrawable(R.drawable.ic_file_download));

        imgSave_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveManga(item, imgSave_Details.getTag().equals("unselected"));
            }
        });
    }

    private void initDagger() {
        DaggerDetailsComponent.builder().detailsModule(new DetailsModule(this, getActivity())).build().inject(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_details;
    }

    @Override
    public void changeActivity(Class mClass) {
        startActivity(new Intent(getActivity(), mClass));
    }

    @Override
    public void addImediateData(MangaListRemoteModel item) {
        GlideUtil.initGlide(getActivity(), item.getCover(), imgCover_Details);
        txtTitle_Details.setText(item.getName());
    }

    @Override
    public void setSaved(boolean saved) {
        if(saved){
            imgSave_Details.setTag("selected");
            imgSave_Details.setImageDrawable(getDrawable(R.drawable.saved_in));
        }else{
            imgSave_Details.setTag("unselected");
            imgSave_Details.setImageDrawable(getDrawable(R.drawable.ic_saved_off));
        }
    }

    @Override
    public void setDetailsData(MangaDetailsRemoteModel item) {
        txtDescription_Details.setText(item.getDescription());
        isOffilne = item.isOffline();
        if(!item.isOffline()) {
            txtYear_Details.setText(item.getRelease_year());
            txtAlterName_Details.setText(item.getAlternative_name());
            txtArtist_Details.setText(item.getArtist());
            txtGenre_Details.setText(item.getGenre());
            txtStatus_Details.setText(item.getStatus());
            txtAutor_Details.setText(item.getAuthor());
        }else{
            cardDetails_Details.setVisibility(View.GONE);
            showFab(false, null);
        }

        ChaptersAdapter chaptersAdapter = new ChaptersAdapter(item.getChapters(), this, getActivity());
        GridLayoutManager llm = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.HORIZONTAL, false);
        rcyChapters_Details.setLayoutManager(llm);
        rcyChapters_Details.setAdapter(chaptersAdapter);
    }

    @Override
    public void setProgressbar(boolean visible) {
        setProgressbarVisible(visible);
        pgrBar_Details.setVisibility(visible? View.VISIBLE: View.INVISIBLE);
    }

    public int refreshActionMode(){
        int nums = ((ChaptersAdapter) rcyChapters_Details.getAdapter()).getSelectedItens().size();
        actionMode.setTitle(nums + " selecionado" + (nums > 1? "s":""));
        return nums;
    }

    @Override
    public void onChapterClick(int id, boolean multSelect) {
        if(multSelect && actionMode != null){
            int nums = refreshActionMode();
            if(nums == 0)
                actionMode.finish();
        }else{
            Intent intent = new Intent(getActivity(), ReadActivity.class);
            intent.putExtra("id", id);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }

    @Override
    public void onLongClick() {
        openMultselect();
        refreshActionMode();
    }

    @Override
    public boolean verifieIfIsDownloaded(int id) {
        return presenter.verifieIfIsDownloaded(id);
    }

    @Override
    public void onClickFab() {
        super.onClickFab();
        if(fabBtn_Default.getTag() == null || fabBtn_Default.getTag() == "unselected"){
            openMultselect();
        }else if(fabBtn_Default.getTag() != null && fabBtn_Default.getTag() == "selected"){
            startDownload();
        }
    }

    private void closeMultselect() {
        fabBtn_Default.setImageDrawable(getDrawable(R.drawable.ic_file_download));
        ((ChaptersAdapter) rcyChapters_Details.getAdapter()).setMultiSelect(false);
        fabBtn_Default.setTag("unselected");
        rcyChapters_Details.getAdapter().notifyDataSetChanged();
    }

    private void openMultselect() {
        fabBtn_Default.setImageDrawable(getDrawable(R.drawable.ic_done));
        ((ChaptersAdapter) rcyChapters_Details.getAdapter()).setMultiSelect(true);
        fabBtn_Default.setTag("selected");
        actionMode = startActionMode(mActionModeCallback);
        rcyChapters_Details.getAdapter().notifyDataSetChanged();
    }

    private void startDownload(){
        String ids = "";
        ArrayList<ChapterMangaRemoteModel> list = ((ChaptersAdapter) rcyChapters_Details.getAdapter()).getSelectedItens();
        if(list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ids += list.get(i).getId() + (i == (list.size() - 1) ? "" : ",");
            }

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(), "download")
                    .setSmallIcon(android.R.drawable.stat_sys_download)
                    .setContentTitle("Preparando download")
                    .setProgress(0, 0, true)
                    .setPriority(NotificationManager.IMPORTANCE_HIGH);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
            notificationManager.notify(1, mBuilder.build());

            presenter.prepareDownload(ids);
            actionMode.finish();
        }else{
            Toast.makeText(getActivity(), "Selecione um capitulo", Toast.LENGTH_SHORT).show();
        }
    }

    ActionMode actionMode;
    Menu context_menu;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.download_chapter, menu);
            context_menu = menu;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId() == R.id.actionmode_download){
                startDownload();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            closeMultselect();
        }
    };

}
