package br.com.mrocigno.projectalicization.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.Services.DownloadServiceTest;
import br.com.mrocigno.projectalicization.Utils.DialogUtil;
import br.com.mrocigno.projectalicization.Utils.GlideUtil;
import br.com.mrocigno.projectalicization.View.DetailsActivity;

public class SavedMangaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity activity;
    ArrayList<Map<String, String>> itens;

    public SavedMangaAdapter(Activity activity, ArrayList<Map<String, String>> itens) {
        this.activity = activity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterViewHolder(LayoutInflater.from(activity).inflate(R.layout.cell_saved_manga, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((AdapterViewHolder) viewHolder).setData(activity, itens.get(i));
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCover_Cellsaved;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCover_Cellsaved = itemView.findViewById(R.id.imgCover_Cellsaved);
        }

        public void setData(final Activity activity, final Map<String, String> item){
            GlideUtil.initGlide(activity, item.get("cover"), imgCover_Cellsaved);
            imgCover_Cellsaved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DetailsActivity.class);
                    intent.putExtra("manga", new MangaListRemoteModel(item.get("name"), item.get("link"), item.get("cover"), item.get("genre"), false, Integer.parseInt(item.get("webid")), 0));
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, imgCover_Cellsaved, "cover");
                    activity.startActivity(intent, options.toBundle());
                }
            });
        }
    }

}
