package br.com.mrocigno.projectalicization.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.Utils.GlideUtil;
import br.com.mrocigno.projectalicization.View.DetailsActivity;

public class SearchMangaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<MangaListRemoteModel> itens;
    Activity activity;
    SearchMangaAdapter.ActionsInterface actionsInterface;

    public SearchMangaAdapter(Activity activity, ArrayList<MangaListRemoteModel> itens, SearchMangaAdapter.ActionsInterface actionsInterface) {
        this.itens = itens;
        this.activity = activity;
        this.actionsInterface = actionsInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterViewHolder(LayoutInflater.from(activity).inflate(R.layout.cell_list_mangas,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((AdapterViewHolder) viewHolder).setData(activity, itens.get(i), actionsInterface);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle_Cellsearch;
        ImageView imgThumb_Cellsearch;
        LinearLayout lnlCell_Cellsearch;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imgThumb_Cellsearch = itemView.findViewById(R.id.imgThumb_Cellsearch);
            txtTitle_Cellsearch = itemView.findViewById(R.id.txtTitle_Cellsearch);
            lnlCell_Cellsearch = itemView.findViewById(R.id.lnlCell_Cellsearch);
        }

        public void setData(final Activity activity, final MangaListRemoteModel item, final SearchMangaAdapter.ActionsInterface actionsInterface){
            txtTitle_Cellsearch.setText(item.getName());
            GlideUtil.initGlide(activity, item.getCover(), imgThumb_Cellsearch);

            lnlCell_Cellsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(actionsInterface.getActivitySearch(), DetailsActivity.class);
                    intent.putExtra("manga", item);
                    // create the transition animation - the images in the layouts
                    // of both activities are defined with android:transitionName="robot"
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(actionsInterface.getActivitySearch(), Pair.create((View)imgThumb_Cellsearch, "cover"), Pair.create((View)txtTitle_Cellsearch, "title"));

                    // start the new activity
                    actionsInterface.getActivitySearch().startActivity(intent, options.toBundle());
                }
            });
        }

    }

    public interface ActionsInterface{
//        void onClickSaveButton(MangaListRemoteModel item, boolean save);
        Activity getActivitySearch();
    }

}
