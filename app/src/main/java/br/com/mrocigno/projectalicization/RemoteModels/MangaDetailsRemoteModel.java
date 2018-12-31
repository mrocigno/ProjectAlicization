package br.com.mrocigno.projectalicization.RemoteModels;

import java.util.ArrayList;

public class MangaDetailsRemoteModel {

    String name;
    String alternateName;
    String yearRelease;
    String status;
    String author;
    String artist;
    String genre;
    String cover;
    String description;
    ArrayList<ChapterMangaRemoteModel> chapters;

    public MangaDetailsRemoteModel(String name, String alternateName, String yearRelease, String status, String author, String artist, String genre, String cover, String description, ArrayList<ChapterMangaRemoteModel> chapters) {
        this.name = name;
        this.alternateName = alternateName;
        this.yearRelease = yearRelease;
        this.status = status;
        this.author = author;
        this.artist = artist;
        this.genre = genre;
        this.cover = cover;
        this.description = description;
        this.chapters = chapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    public String getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(String yearRelease) {
        this.yearRelease = yearRelease;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ChapterMangaRemoteModel> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<ChapterMangaRemoteModel> chapters) {
        this.chapters = chapters;
    }
}
