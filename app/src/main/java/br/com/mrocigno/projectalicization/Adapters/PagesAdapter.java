package br.com.mrocigno.projectalicization.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;
import br.com.mrocigno.projectalicization.Utils.GlideUtil;

public class PagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity activity;
    ArrayList<PagesDataRemoteModel.Pages> itens;

    public PagesAdapter(Activity activity, ArrayList<PagesDataRemoteModel.Pages> itens) {
        this.activity = activity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterViewHolder(LayoutInflater.from(activity).inflate(R.layout.cell_page, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((AdapterViewHolder) viewHolder).setData(activity, itens.get(i));
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPage_Cellpage;
        Button reload;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPage_Cellpage = itemView.findViewById(R.id.imgPage_Cellpage);
            reload = itemView.findViewById(R.id.reload);
        }

        public void setData(final Activity activity, final PagesDataRemoteModel.Pages item){
            GlideUtil.initGlide(activity, "http://traduzame.esy.es/pageJPG.php?link=" + item.getLink(), imgPage_Cellpage);
            reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlideUtil.initGlide(activity, "http://traduzame.esy.es/pageJPG.php?link=" + item.getLink(), imgPage_Cellpage);
                }
            });
        }
    }
}
