package br.com.mrocigno.projectalicization.RemoteModels;

import java.io.Serializable;

public class MangaListRemoteModel implements Serializable {

    String name;
    String link;
    String cover;
    String genre;
    boolean isSaved;
    int id;
    int num_chapters;

    public MangaListRemoteModel(String name, String link, String cover, String genre, boolean isSaved, int id, int num_chapters) {
        this.name = name;
        this.link = link;
        this.cover = cover;
        this.genre = genre;
        this.isSaved = isSaved;
        this.id = id;
        this.num_chapters = num_chapters;
    }

    public int getNum_chapters() {
        return num_chapters;
    }

    public void setNum_chapters(int num_chapters) {
        this.num_chapters = num_chapters;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}
