package br.com.mrocigno.projectalicization.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.ChapterMangaRemoteModel;

public class ChaptersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ChapterMangaRemoteModel> itens;
    ActionsChaptersAdapter actions;
    Activity activity;

    ArrayList<ChapterMangaRemoteModel> selectedItens;

    public ArrayList<ChapterMangaRemoteModel> getSelectedItens() {
        return selectedItens;
    }

    public void setSelectedItens(ArrayList<ChapterMangaRemoteModel> selectedItens) {
        this.selectedItens = selectedItens;
    }

    boolean multiSelect = false;

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
        if(!multiSelect){
            selectedItens = new ArrayList<>();
            for (int i = 0; i < itens.size(); i++) {
                itens.get(i).setSelected(false);
            }
        }
    }

    public ChaptersAdapter(ArrayList<ChapterMangaRemoteModel> itens, ActionsChaptersAdapter actions, Activity activity) {
        this.itens = itens;
        this.actions = actions;
        this.activity = activity;
        selectedItens = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterViewHolder(LayoutInflater.from(activity).inflate(R.layout.cell_chapters, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((AdapterViewHolder) viewHolder).setData(itens.get(i), actions, isMultiSelect());
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder {

        LinearLayout lnlCell_Cellchapter;
        TextView txtTitle_Cellchapter;
        CheckBox cbxSelect_Cellchapter;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            lnlCell_Cellchapter = itemView.findViewById(R.id.lnlCell_Cellchapter);
            txtTitle_Cellchapter = itemView.findViewById(R.id.txtTitle_Cellchapter);
            cbxSelect_Cellchapter = itemView.findViewById(R.id.cbxSelect_Cellchapter);
        }

        public void setData(final ChapterMangaRemoteModel item, final ActionsChaptersAdapter actions, boolean multiSelect){
            if(multiSelect){
                cbxSelect_Cellchapter.setVisibility(View.VISIBLE);
            }else{
                cbxSelect_Cellchapter.setVisibility(View.INVISIBLE);
            }
            if(item.isSelected()) {
                lnlCell_Cellchapter.setBackgroundColor(Color.LTGRAY);
                cbxSelect_Cellchapter.setChecked(true);
            }else{
                lnlCell_Cellchapter.setBackground(activity.getDrawable(R.drawable.border_bottom_center_gray));
                cbxSelect_Cellchapter.setChecked(false);
            }

            txtTitle_Cellchapter.setText(item.getChapterName());
            lnlCell_Cellchapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isMultiSelect()){
                        if(item.isSelected()){
                            item.setSelected(false);
                            lnlCell_Cellchapter.setBackground(activity.getDrawable(R.drawable.border_bottom_center_gray));
                            cbxSelect_Cellchapter.setChecked(false);
                            selectedItens.remove(item);
                        }else{
                            item.setSelected(true);
                            lnlCell_Cellchapter.setBackgroundColor(Color.LTGRAY);
                            cbxSelect_Cellchapter.setChecked(true);
                            selectedItens.add(item);
                        }
                    }
                    actions.onChapterClick(item.getChapterLink(), isMultiSelect());
                }
            });

            lnlCell_Cellchapter.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(!isMultiSelect()){
                        setMultiSelect(true);
                        item.setSelected(true);
                        lnlCell_Cellchapter.setBackgroundColor(Color.LTGRAY);
                        cbxSelect_Cellchapter.setChecked(true);
                        selectedItens.add(item);
                        actions.onLongClick();
                    }
                    return false;
                }
            });
        }
    }

    public interface ActionsChaptersAdapter{
        void onChapterClick(String link, boolean multSelect);
        void onLongClick();
    }
}
