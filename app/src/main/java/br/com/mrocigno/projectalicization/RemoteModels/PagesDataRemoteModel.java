package br.com.mrocigno.projectalicization.RemoteModels;

import java.util.ArrayList;

public class PagesDataRemoteModel {
    int numPages;
    ArrayList<Pages> pages;

    public PagesDataRemoteModel(int numPages, ArrayList<Pages> pages) {
        this.numPages = numPages;
        this.pages = pages;
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

    public class Pages{
        int id;
        int id_chapter;
        int num_page;
        String link_page;
        String date_added;
        String link_jpg;

        public Pages(int id, int id_chapter, int num_page, String link_page, String date_added, String link_jpg) {
            this.id = id;
            this.id_chapter = id_chapter;
            this.num_page = num_page;
            this.link_page = link_page;
            this.date_added = date_added;
            this.link_jpg = link_jpg;
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
    }
}
