package br.com.mrocigno.projectalicization.RemoteModels;

import java.io.Serializable;
import java.util.ArrayList;

public class DownloadMangaRemoteModel implements Serializable {
    int id;
    String name;
    String link;
    String cover;
    ArrayList<Chapters> chapters;

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

    public ArrayList<Chapters> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapters> chapters) {
        this.chapters = chapters;
    }

    public class Chapters implements Serializable{
        int id;
        int num_pages;
        String name_chapter;
        String link_chapter;
        ArrayList<Pages> pages;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public ArrayList<Pages> getPages() {
            return pages;
        }

        public void setPages(ArrayList<Pages> pages) {
            this.pages = pages;
        }

        public class Pages implements Serializable {
            int id;
            int num_page;
            String link_page;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
        }
    }
}
