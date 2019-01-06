package br.com.mrocigno.projectalicization.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.ChapterMangaRemoteModel;

public class ChaptersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ChapterMangaRemoteModel> itens;
    ActionsChaptersAdapter actions;
    Activity activity;

    public ChaptersAdapter(ArrayList<ChapterMangaRemoteModel> itens, ActionsChaptersAdapter actions, Activity activity) {
        this.itens = itens;
        this.actions = actions;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterViewHolder(LayoutInflater.from(activity).inflate(R.layout.cell_chapters, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((AdapterViewHolder) viewHolder).setData(itens.get(i), actions);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder {

        LinearLayout lnlCell_Cellchapter;

        TextView txtTitle_Cellchapter;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            lnlCell_Cellchapter = itemView.findViewById(R.id.lnlCell_Cellchapter);
            txtTitle_Cellchapter = itemView.findViewById(R.id.txtTitle_Cellchapter);
        }

        public void setData(final ChapterMangaRemoteModel item, final ActionsChaptersAdapter actions){
            txtTitle_Cellchapter.setText(item.getChapterName());
            lnlCell_Cellchapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actions.onChapterClick(item.getChapterLink());
                }
            });
        }
    }

    public interface ActionsChaptersAdapter{
        void onChapterClick(String link);
    }
}
