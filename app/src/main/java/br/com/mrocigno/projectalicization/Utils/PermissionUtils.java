package br.com.mrocigno.projectalicization.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import br.com.mrocigno.projectalicization.Database.Preferences;

public class PermissionUtils {
    public static void checkPermission(Activity context, String permission, PermissionAskListener listener) {
        // if permission not granted
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionResult = ActivityCompat.checkSelfPermission(context, permission);

            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                Preferences preferences = Preferences.getInstance(context);
                if (preferences.isFirstTimeAskingPermission(permission)) {
                    preferences.setFirstTimeAskingPermission(permission, false);
                    listener.onFirstTimeAsked();
                }else{
                    if(!context.shouldShowRequestPermissionRationale(permission)){
                        listener.onPermanentlyDenied();
                    }else{
                        listener.onPermissionDisabled();
                    }
                }
            } else {
                listener.onPermissionGranted();
            }
        } else {
            listener.onPermissionGranted();
        }
    }


    /**
     * The interface Permission ask listener.
     */
    public interface PermissionAskListener {

        /**
         * Callback if is the first time permission asked
         */
        void onFirstTimeAsked();

        /**
         * Callback on permission denied
         */
        void onPermanentlyDenied();

        /**
         * Callback on permission "Never show again" checked and denied
         */
        void onPermissionDisabled();

        /**
         * Callback on permission granted
         */
        void onPermissionGranted();


    }
}
