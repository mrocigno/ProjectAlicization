package br.com.mrocigno.projectalicization.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.Helpers.CustomRelativeLayout;
import br.com.mrocigno.projectalicization.Modules.DataModule;
import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;
import br.com.mrocigno.projectalicization.Utils.Util;

public class PagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity activity;
    ArrayList<PagesDataRemoteModel.Pages> itens;
    boolean clear;

    public void setClear(boolean clear) {
        this.clear = clear;
    }

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
        if(clear)
            ((AdapterViewHolder) viewHolder).clear();
        else
            ((AdapterViewHolder) viewHolder).setData(itens.get(i));
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPage_Cellpage;

        LinearLayout lnlProgress_Read;
        TextView txtMsgProgress_Read;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPage_Cellpage = itemView.findViewById(R.id.imgPage_Cellpage);
            lnlProgress_Read = itemView.findViewById(R.id.lnlProgress_Cellpage);
            txtMsgProgress_Read = itemView.findViewById(R.id.txtMsgProgress_Cellpage);

        }

        public void clear(){
            imgPage_Cellpage.setImageBitmap(null);
            imgPage_Cellpage = null;
        }

        public void setData(final PagesDataRemoteModel.Pages item){

            Picasso.get().load(DataModule.BASE_URL + "pageJPG.php?id=" + item.getId()).into(imgPage_Cellpage, new Callback() {
                @Override
                public void onSuccess() {
                    lnlProgress_Read.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    txtMsgProgress_Read.setText("A página não pode ser carregada");
                }
            });

        }

    }
}
