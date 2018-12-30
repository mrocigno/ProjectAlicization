package br.com.mrocigno.projectalicization.RemoteModels;

import java.util.ArrayList;

public class BaseArrayDataRemoteModel<T> {

    int status;
    String message;
    ArrayList<T> data;

    public BaseArrayDataRemoteModel(int status, String message, ArrayList<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
