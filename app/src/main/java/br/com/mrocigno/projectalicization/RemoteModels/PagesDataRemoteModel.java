package br.com.mrocigno.projectalicization.RemoteModels;

import java.util.ArrayList;

public class PagesDataRemoteModel {
    int numPages;
    boolean isOffine;
    ArrayList<Pages> pages;

    public PagesDataRemoteModel(int numPages, ArrayList<Pages> pages, boolean isOffine) {
        this.numPages = numPages;
        this.pages = pages;
        this.isOffine = isOffine;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public ArrayList<Pages> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Pages> pages) {
        this.pages = pages;
    }

    public boolean isOffine() {
        return isOffine;
    }

    public void setOffine(boolean offine) {
        isOffine = offine;
    }

    public static class Pages{
        int id;
        int id_chapter;
        int num_page;
        String link_page;
        String date_added;
        String link_jpg;
        String local_path;
        boolean isOffine;


        public Pages(int id, int id_chapter, int num_page, String link_page, String local_path, boolean isOffine) {
            this.id = id;
            this.id_chapter = id_chapter;
            this.num_page = num_page;
            this.local_path = local_path;
            this.isOffine = isOffine;
            this.link_page = link_page;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId_chapter() {
            return id_chapter;
        }

        public void setId_chapter(int id_chapter) {
            this.id_chapter = id_chapter;
        }

        public int getNum_page() {
            return num_page;
        }

        public void setNum_page(int num_page) {
            this.num_page = num_page;
        }

        public String getLink_page() {
            return link_page;
        }

        public void setLink_page(String link_page) {
            this.link_page = link_page;
        }

        public String getDate_added() {
            return date_added;
        }

        public void setDate_added(String date_added) {
            this.date_added = date_added;
        }

        public String getLink_jpg() {
            return link_jpg;
        }

        public void setLink_jpg(String link_jpg) {
            this.link_jpg = link_jpg;
        }

        public String getLocal_path() {
            return local_path;
        }

        public void setLocal_path(String local_path) {
            this.local_path = local_path;
        }

        public boolean isOffine() {
            return isOffine;
        }

        public void setOffine(boolean offine) {
            isOffine = offine;
        }
    }
}
