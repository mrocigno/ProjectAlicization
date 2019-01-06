package br.com.mrocigno.projectalicization.Config;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.io.Serializable;

public abstract class MyFragment<T> extends Fragment implements MyView<T> {

    public T presenter;

    public MyActivity getMyActivity(){
        if(getActivity() instanceof MyActivity){
            return (MyActivity) getActivity();
        }
        return null;
    }

    @Override
    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }
}
