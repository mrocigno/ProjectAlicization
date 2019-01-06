package br.com.mrocigno.projectalicization.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.mrocigno.projectalicization.R;

public class DialogUtil {

    public static AlertDialog inflateLayoutDialog(Context context, View view){
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context, R.style.AlertDialogThemeTransparent);
        alertBuilder.setCancelable(false);
        alertBuilder.setView(view);
        return alertBuilder.create();
    }

    public static void inflateLayoutDialog(Context context, String msg, String actBtn, boolean canceable, View.OnClickListener callback){
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context, R.style.AlertDialogThemeTransparent);

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null);
        TextView txtMsg_Dialog = view.findViewById(R.id.txtMsg_Dialog);
        Button btnOk_Dialog = view.findViewById(R.id.btnOk_Dialog);

        txtMsg_Dialog.setText(msg);
        btnOk_Dialog.setText(actBtn);
        btnOk_Dialog.setOnClickListener(callback);

        alertBuilder.setCancelable(canceable);
        alertBuilder.setView(view);
        alertBuilder.create().show();
    }


}
