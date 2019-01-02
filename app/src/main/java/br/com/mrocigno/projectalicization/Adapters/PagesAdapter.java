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

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.mrocigno.projectalicization.R;
import br.com.mrocigno.projectalicization.RemoteModels.PagesDataRemoteModel;

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
            ((AdapterViewHolder) viewHolder).setData(activity, itens.get(i));
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{

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

        public void setData(final Activity activity, final PagesDataRemoteModel.Pages item){
            Picasso.get().load("http://traduzame.esy.es/pageJPG.php?link=" + item.getLink()).into(imgPage_Cellpage, new Callback() {
                @Override
                public void onSuccess() {
                    lnlProgress_Read.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    txtMsgProgress_Read.setText("A página não pode ser carregada");
                }
            });

//            PicassoUtil.initGlide(activity, "http://traduzame.esy.es/pageJPG.php?link=" + item.getLink(), imgPage_Cellpage);
//            reload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    PicassoUtil.initGlide(activity, "http://traduzame.esy.es/pageJPG.php?link=" + item.getLink(), imgPage_Cellpage);
//                }
//            });
//            new Retrofit.Builder()
//                    .baseUrl("http://traduzame.esy.es/").build().create(MyNetworkRoutes.class).getPageJPG(item.getLink()).enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    try {
//                        imgPage_Cellpage.setImageBitmap(BitmapFactory.decodeByteArray(response.body().bytes(), 0, response.body().bytes().length));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                }
//            });
        }
    }
}