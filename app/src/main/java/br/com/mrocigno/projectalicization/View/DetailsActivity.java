package br.com.mrocigno.projectalicization.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Components.DaggerDetailsComponent;
import br.com.mrocigno.projectalicization.Config.MyActivity;
import br.com.mrocigno.projectalicization.Modules.DetailsModule;
import br.com.mrocigno.projectalicization.Presenter.DetailsInterface;
import br.com.mrocigno.projectalicization.Presenter.DetailsPresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.Utils.GlideUtil;

public class DetailsActivity extends MyActivity implements DetailsInterface {

    @Inject
    DetailsPresenter presenter;

    TextView txtTitle_Details;
    ImageView imgCover_Details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDagger();

        txtTitle_Details = findViewById(R.id.txtTitle_Details);
        imgCover_Details = findViewById(R.id.imgCover_Details);

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

    }

    @Override
    public void addImediateData(MangaListRemoteModel item) {
        GlideUtil.initGlide(getActivity(), item.getCover(), imgCover_Details);
        txtTitle_Details.setText(item.getName());
    }
}
