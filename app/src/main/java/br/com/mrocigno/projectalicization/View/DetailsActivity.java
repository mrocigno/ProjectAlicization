package br.com.mrocigno.projectalicization.View;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Adapters.ChaptersAdapter;
import br.com.mrocigno.projectalicization.Components.DaggerDetailsComponent;
import br.com.mrocigno.projectalicization.Config.MyActivity;
import br.com.mrocigno.projectalicization.Modules.DetailsModule;
import br.com.mrocigno.projectalicization.Presenter.DetailsInterface;
import br.com.mrocigno.projectalicization.Presenter.DetailsPresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.MangaDetailsRemoteModel;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.Utils.PicassoUtil;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        super.onCreate(savedInstanceState);

        initDagger();

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

        Intent intent = getIntent();
        if(intent.hasExtra("manga")){
            presenter.loadData(intent);
        }else{
            finish();
        }
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
        PicassoUtil.initGlide(getActivity(), item.getCover(), imgCover_Details);
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
        txtYear_Details.setText(item.getYearRelease());
        txtAlterName_Details.setText(item.getAlternateName());
        txtArtist_Details.setText(item.getArtist());
        txtGenre_Details.setText(item.getGenre());
        txtStatus_Details.setText(item.getStatus());
        txtAutor_Details.setText(item.getAuthor());

        ChaptersAdapter chaptersAdapter = new ChaptersAdapter(item.getChapters(), this, getActivity());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcyChapters_Details.setLayoutManager(llm);
        rcyChapters_Details.setAdapter(chaptersAdapter);
    }

    @Override
    public void setProgressbar(boolean visible) {
        setProgressbarVisible(visible);
        pgrBar_Details.setVisibility(visible? View.VISIBLE: View.INVISIBLE);
    }

    @Override
    public void onChapterClick(String link) {
        Intent intent = new Intent(getActivity(), ReadActivity.class);
        intent.putExtra("link", link);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

}
