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
import android.widget.TextView;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.MangaListRemoteModel;
import br.com.mrocigno.projectalicization.Utils.GlideUtil;
import br.com.mrocigno.projectalicization.View.DetailsActivity;

public class MangaThumbAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<MangaListRemoteModel> itens;
    Activity activity;
    ActionsInterface actionsInterface;

    public MangaThumbAdapter(ArrayList<MangaListRemoteModel> itens, Activity activity, ActionsInterface actionsInterface) {
        this.itens = itens;
        this.activity = activity;
        this.actionsInterface = actionsInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterViewHolder(LayoutInflater.from(activity).inflate(R.layout.cell_thumb_mangas, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((AdapterViewHolder) viewHolder).setData(activity, itens.get(i), actionsInterface);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle_Cellthumb;
        ImageView imgThumb_Cellthumb;
        ImageView imgSave_Cellthumb;
        CardView cardCell_Cellthumb;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imgThumb_Cellthumb = itemView.findViewById(R.id.imgThumb_Cellthumb);
            txtTitle_Cellthumb = itemView.findViewById(R.id.txtTitle_Cellthumb);
            imgSave_Cellthumb = itemView.findViewById(R.id.imgSave_Cellthumb);
            cardCell_Cellthumb = itemView.findViewById(R.id.cardCell_Cellthumb);

        }

        public void setData(final Activity activity, final MangaListRemoteModel item, final ActionsInterface actionsInterface){
            txtTitle_Cellthumb.setText(item.getName());
            GlideUtil.initGlide(activity, item.getCover(), imgThumb_Cellthumb);

            if(actionsInterface.verifieIfSaved(item)){
                item.setSaved(true);
                imgSave_Cellthumb.setImageDrawable(activity.getDrawable(R.drawable.saved_in));
                imgSave_Cellthumb.setTag("selected");
            }

            imgSave_Cellthumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(imgSave_Cellthumb.getTag().equals("selected")){
                        imgSave_Cellthumb.setImageDrawable(activity.getDrawable(R.drawable.ic_saved_off));
                        imgSave_Cellthumb.setTag("unselected");
                        actionsInterface.onClickSaveButton(item, false);
                    }else{
                        imgSave_Cellthumb.setImageDrawable(activity.getDrawable(R.drawable.saved_in));
                        imgSave_Cellthumb.setTag("selected");
                        actionsInterface.onClickSaveButton(item, true);
                    }
                }
            });

            cardCell_Cellthumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(actionsInterface.getActivityThumb(), DetailsActivity.class);
                    intent.putExtra("manga", item);
                    // create the transition animation - the images in the layouts
                    // of both activities are defined with android:transitionName="robot"
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(actionsInterface.getActivityThumb(), Pair.create((View)imgThumb_Cellthumb, "cover"), Pair.create((View)txtTitle_Cellthumb, "title"), Pair.create((View)imgSave_Cellthumb, "save"));

                    // start the new activity
                    actionsInterface.getActivityThumb().startActivity(intent, options.toBundle());
                }
            });
        }
    }

    public interface ActionsInterface{
        void onClickSaveButton(MangaListRemoteModel item, boolean save);
        boolean verifieIfSaved(MangaListRemoteModel item);
        Activity getActivityThumb();
    }
}
