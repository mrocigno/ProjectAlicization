package br.com.mrocigno.projectalicization.RemoteModels;

import java.io.Serializable;

public class MangaListRemoteModel implements Serializable {

    String name;
    String link;
    String cover;
    String genre;
    int webid;

    public MangaListRemoteModel(String name, String link, String cover, String genre, int webid) {
        this.name = name;
        this.link = link;
        this.cover = cover;
        this.genre = genre;
        this.webid = webid;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getWebid() {
        return webid;
    }

    public void setWebid(int webid) {
        this.webid = webid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
