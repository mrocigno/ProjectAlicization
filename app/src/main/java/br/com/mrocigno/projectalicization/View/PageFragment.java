package br.com.mrocigno.projectalicization.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.Utils.GlideUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    ImageView imgPage_Cellpage;

    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment newInstance(String link) {

        Bundle args = new Bundle();
        args.putString("link", link);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cell_page , container, false);

        imgPage_Cellpage = view.findViewById(R.id.imgPage_Cellpage);

        GlideUtil.initGlide(getActivity(),"http://traduzame.esy.es/pageJPG.php?link=" +  getArguments().getString("link"), imgPage_Cellpage);

        return view;
    }

}
