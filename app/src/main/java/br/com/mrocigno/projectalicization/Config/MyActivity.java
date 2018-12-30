package br.com.mrocigno.projectalicization.Config;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import br.com.mrocigno.projectalicization.R;

public abstract class MyActivity extends AppCompatActivity implements MyView {

    String TAG = "TESTEEE";

    FrameLayout defaultContainer;
    Toolbar toolbar;
    ProgressBar pgrBar_Deafult;
    SearchView srvSearch_Default;
    ImageView imgLogo_Default;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        defaultContainer = findViewById(R.id.defaultContainer);
        pgrBar_Deafult = findViewById(R.id.pgrBar_Deafult);
        srvSearch_Default = findViewById(R.id.srvSearch_Default);
        imgLogo_Default = findViewById(R.id.imgLogo_Default);

        srvSearch_Default.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLogo_Default.setVisibility(View.GONE);
                toolbar.getMenu().clear();
            }
        });

        srvSearch_Default.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                imgLogo_Default.setVisibility(View.VISIBLE);
                toolbar.inflateMenu(R.menu.main_menu);
                return false;
            }
        });

        View v = getLayoutInflater().inflate(getLayoutRes(), null);
        defaultContainer.addView(v);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public abstract int getLayoutRes();

    @Override
    public Activity getActivity() {
        return this;
    }

    public void setProgressbarVisible(boolean visible){
        pgrBar_Deafult.setVisibility(visible? View.VISIBLE:View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if(!srvSearch_Default.isIconified()){
            srvSearch_Default.setIconified(true);
        }else{
            super.onBackPressed();
        }
    }
}
