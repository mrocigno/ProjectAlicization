package br.com.mrocigno.projectalicization.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import br.com.mrocigno.projectalicization.Config.MyActivity;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.Utils.GlideUtil;

public class DetailsActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);
        GlideUtil.initGlide(this, getIntent().getStringExtra("cover"), (ImageView) findViewById(R.id.imgCover_Details));
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_details;
    }

    @Override
    public void changeActivity(Class mClass) {

    }
}
