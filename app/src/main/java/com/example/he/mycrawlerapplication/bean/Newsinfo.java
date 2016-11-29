package com.example.he.mycrawlerapplication.bean;

/**
 * Created by HE on 2016/11/27.
 */

public class Newsinfo {
    private String titel;
    private String url;
    private String image;

    public Newsinfo(String titel, String url, String image) {
        this.titel = titel;
        this.url = url;
        this.image = image;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
