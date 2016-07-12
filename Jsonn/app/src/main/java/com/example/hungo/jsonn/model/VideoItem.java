package com.example.hungo.jsonn.model;

import java.io.Serializable;

/**
 * Created by hungo on 7/10/2016.
 */
public class VideoItem implements Serializable {
    String tiltle;
    String description;
    String publishedAt;
    String url;

    public VideoItem(){

    }
    public VideoItem(String title, String description, String publishedAt, String url){
        this.tiltle = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTiltle() {
        return tiltle;
    }

    public void setTiltle(String tiltle) {
        this.tiltle = tiltle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public  VideoItem(String tiltle){
        super();
        this.tiltle = tiltle;

    }
}
