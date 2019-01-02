package br.com.mrocigno.projectalicization.RemoteModels;

import java.util.ArrayList;

public class PagesDataRemoteModel {
    int numPages;
    String firstJPG;
    ArrayList<Pages> pages;

    public PagesDataRemoteModel(int numPages, String firstJPG, ArrayList<Pages> pages) {
        this.numPages = numPages;
        this.firstJPG = firstJPG;
        this.pages = pages;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getFirstJPG() {
        return firstJPG;
    }

    public void setFirstJPG(String firstJPG) {
        this.firstJPG = firstJPG;
    }

    public ArrayList<Pages> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Pages> pages) {
        this.pages = pages;
    }

    public class Pages{
        String link;

        public Pages(String link) {
            this.link = link;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
