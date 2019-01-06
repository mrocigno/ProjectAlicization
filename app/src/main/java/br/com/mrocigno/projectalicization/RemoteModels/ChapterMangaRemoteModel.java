package br.com.mrocigno.projectalicization.RemoteModels;

public class ChapterMangaRemoteModel {

    int chapterNumber;
    String chapterName;
    String chapterReleaseDate;
    String chapterLink;
    boolean selected;

    public ChapterMangaRemoteModel(int chapterNumber, String chapterName, String chapterReleaseDate, String chapterLink) {
        this.chapterNumber = chapterNumber;
        this.chapterName = chapterName;
        this.chapterReleaseDate = chapterReleaseDate;
        this.chapterLink = chapterLink;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterReleaseDate() {
        return chapterReleaseDate;
    }

    public void setChapterReleaseDate(String chapterReleaseDate) {
        this.chapterReleaseDate = chapterReleaseDate;
    }

    public String getChapterLink() {
        return chapterLink;
    }

    public void setChapterLink(String chapterLink) {
        this.chapterLink = chapterLink;
    }
}
