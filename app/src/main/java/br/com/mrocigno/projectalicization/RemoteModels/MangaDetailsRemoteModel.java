package br.com.mrocigno.projectalicization.RemoteModels;

import java.util.ArrayList;

public class MangaDetailsRemoteModel {

    int id;
    int num_chapters;
    String name;
    String alternative_name;
    String release_year;
    String date_added;
    String status;
    String author;
    String artist;
    String genre;
    String cover;
    String background_cover;
    String link;
    String description;
    String font;
    ArrayList<ChapterMangaRemoteModel> chapters;
    boolean offline;

    public MangaDetailsRemoteModel(int id, int num_chapters, String name, String alternative_name, String release_year, String date_added, String status, String author, String artist, String genre, String cover, String background_cover, String link, String description, String font, ArrayList<ChapterMangaRemoteModel> chapters) {
        this.id = id;
        this.num_chapters = num_chapters;
        this.name = name;
        this.alternative_name = alternative_name;
        this.release_year = release_year;
        this.date_added = date_added;
        this.status = status;
        this.author = author;
        this.artist = artist;
        this.genre = genre;
        this.cover = cover;
        this.background_cover = background_cover;
        this.link = link;
        this.description = description;
        this.font = font;
        this.chapters = chapters;
    }

    public MangaDetailsRemoteModel(int id, String description, ArrayList<ChapterMangaRemoteModel> chapters, boolean offline) {
        this.id = id;
        this.description = description;
        this.chapters = chapters;
        this.offline = offline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_chapters() {
        return num_chapters;
    }

    public void setNum_chapters(int num_chapters) {
        this.num_chapters = num_chapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternative_name() {
        return alternative_name;
    }

    public void setAlternative_name(String alternative_name) {
        this.alternative_name = alternative_name;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBackground_cover() {
        return background_cover;
    }

    public void setBackground_cover(String background_cover) {
        this.background_cover = background_cover;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public ArrayList<ChapterMangaRemoteModel> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<ChapterMangaRemoteModel> chapters) {
        this.chapters = chapters;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }
}

