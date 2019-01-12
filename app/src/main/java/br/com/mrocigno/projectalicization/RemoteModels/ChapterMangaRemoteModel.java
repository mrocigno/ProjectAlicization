package br.com.mrocigno.projectalicization.RemoteModels;

public class ChapterMangaRemoteModel {

    int id;
    int id_manga;
    int num_pages;
    String name_chapter;
    String link_chapter;
    String date_updated;
    boolean selected;

    public ChapterMangaRemoteModel(int id, int id_manga, int num_pages, String name_chapter, String link_chapter, String date_updated, boolean selected) {
        this.id = id;
        this.id_manga = id_manga;
        this.num_pages = num_pages;
        this.name_chapter = name_chapter;
        this.link_chapter = link_chapter;
        this.date_updated = date_updated;
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_manga() {
        return id_manga;
    }

    public void setId_manga(int id_manga) {
        this.id_manga = id_manga;
    }

    public int getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(int num_pages) {
        this.num_pages = num_pages;
    }

    public String getName_chapter() {
        return name_chapter;
    }

    public void setName_chapter(String name_chapter) {
        this.name_chapter = name_chapter;
    }

    public String getLink_chapter() {
        return link_chapter;
    }

    public void setLink_chapter(String link_chapter) {
        this.link_chapter = link_chapter;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
