package br.com.mrocigno.projectalicization.Config;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import br.com.mrocigno.projectalicization.Database.Preferences;
import br.com.mrocigno.projectalicization.R;

public abstract class MyActivity extends AppCompatActivity {

    public String TAG = "TESTEEE";

    FloatingActionButton fabBtn_Default;
    FrameLayout defaultContainer;
    Toolbar toolbar;
    ProgressBar pgrBar_Deafult;
    SearchView srvSearch_Default;
    ImageView imgLogo_Default;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(Preferences.getInstace(getActivity()).isDarkTheme()){
            this.setTheme(R.style.AppThemeDark);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        defaultContainer = findViewById(R.id.defaultContainer);
        fabBtn_Default = findViewById(R.id.fabBtn_Default);
        pgrBar_Deafult = findViewById(R.id.pgrBar_Deafult);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(showToolbar()){
            srvSearch_Default = findViewById(R.id.srvSearch_Default);
            imgLogo_Default = findViewById(R.id.imgLogo_Default);

            srvSearch_Default.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSearchViewClick();
                }
            });

            srvSearch_Default.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    onSearchViewTextSubmit(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    onSearchViewTextChange(s);
                    return false;
                }
            });

            srvSearch_Default.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    onSearchViewClose();
                    return false;
                }
            });
        }else{
            toolbar.setVisibility(View.GONE);
            pgrBar_Deafult.setVisibility(View.GONE);
        }



        View v = getLayoutInflater().inflate(getLayoutRes(), null);
        defaultContainer.addView(v);
    }

    public void onSearchViewClose() {
        imgLogo_Default.setVisibility(View.VISIBLE);
        toolbar.inflateMenu(R.menu.main_menu);
    }

    public void onSearchViewClick() {
        imgLogo_Default.setVisibility(View.GONE);
        toolbar.getMenu().clear();
    }

    public void onSearchViewTextSubmit(String s){

    }

    public void onSearchViewTextChange(String s){

    }

    public abstract int getLayoutRes();

    public abstract void changeActivity(Class mClass);

    public Activity getActivity() {
        return this;
    }

    public void setProgressbarVisible(boolean visible){
        pgrBar_Deafult.setVisibility(visible? View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public SearchView getSrvSearch_Default() {
        return srvSearch_Default;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuDarkTheme) {
            Preferences pref = Preferences.getInstace(getActivity());
            if(pref.isDarkTheme()){
                pref.setDarkTheme(false);
                item.setTitle("Tema escuro");
            }else{
                pref.setDarkTheme(true);
                item.setTitle("Tema claro");
            }
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if(srvSearch_Default != null && !srvSearch_Default.isIconified()){
            srvSearch_Default.setQuery("", false);
            srvSearch_Default.setIconified(true);
        }else{
//            finish();
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Runtime.getRuntime().gc();
        System.gc();
    }

    public boolean showToolbar(){
        return true;
    }

    @SuppressLint("RestrictedApi")
    public void showFab(boolean show){
        fabBtn_Default.setVisibility(show? View.VISIBLE: View.GONE);
    }
}
