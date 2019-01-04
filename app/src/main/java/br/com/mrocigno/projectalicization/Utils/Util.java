package br.com.mrocigno.projectalicization.Utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class Util {
    public static float pxToDp(int px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
