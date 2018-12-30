package br.com.mrocigno.projectalicization.RemoteModels;

public class ListRemoteModels {

    String name;
    String link;
    String cover;
    int webid;

    public ListRemoteModels(String name, String link, String cover, int webid) {
        this.name = name;
        this.link = link;
        this.cover = cover;
        this.webid = webid;
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
