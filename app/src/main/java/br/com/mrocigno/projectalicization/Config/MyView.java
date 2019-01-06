package br.com.mrocigno.projectalicization.Config;

import android.app.Activity;

public interface MyView<T> {
//    void changeActivity(Class mClass);
    Activity getActivity();
    void setPresenter(T presenter);
}
