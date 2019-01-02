package br.com.mrocigno.projectalicization.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

import br.com.mrocigno.projectalicization.Adapters.PagesAdapter;
import br.com.mrocigno.projectalicization.Adapters.ViewPagerAdapter;
import br.com.mrocigno.projectalicization.Components.DaggerReadComponent;
import br.com.mrocigno.projectalicization.Config.MyActivity;
import br.com.mrocigno.projectalicization.Config.MyView;
import br.com.mrocigno.projectalicization.Modules.ReadModule;
import br.com.mrocigno.projectalicization.Presenter.ReadInterface;
import br.com.mrocigno.projectalicization.Presenter.ReadPresenter;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;

public class ReadActivity extends MyActivity implements ReadInterface {

    @Inject
    ReadPresenter presenter;

    RecyclerView rcyPages_Read;

    LinearLayout lnlProgress_Read;

    TextView txtMsgProgress_Read;

//    ViewPager viewPager_Read;
    ImageSwitcher imgSwitcher_Read;

    int oldPage = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rcyPages_Read = findViewById(R.id.rcyPages_Read);
        lnlProgress_Read = findViewById(R.id.lnlProgress_Read);
        txtMsgProgress_Read = findViewById(R.id.txtMsgProgress_Read);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rcyPages_Read);

        DaggerReadComponent.builder().readModule(new ReadModule(this)).build().inject(this);

        Intent intent = getIntent();
        if(intent.hasExtra("link")){
            presenter.loadData(intent.getStringExtra("link"));
        }else{
            finish();
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_read;
    }

    @Override
    public void changeActivity(Class mClass) {

    }

    @Override
    public void showPages(PagesDataRemoteModel pages) {
//        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        ArrayList<Fragment> list = new ArrayList<>();
//        for (int i = 0; i < pages.getPages().size(); i++) {
//            list.add(PageFragment.newInstance(pages.getPages().get(i).getLink()));
//        }
//        pagerAdapter.listFragments(list);
//        viewPager_Read.setAdapter(pagerAdapter);
        PagesAdapter pagesAdapter = new PagesAdapter(getActivity(), pages.getPages());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcyPages_Read.setLayoutManager(llm);
        rcyPages_Read.setAdapter(pagesAdapter);
    }

    @Override
    public void setLoadProgress(boolean visible) {
        lnlProgress_Read.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setMsgProgress(String string) {
        txtMsgProgress_Read.setText(string);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean showToolbar() {
        return false;
    }
}
