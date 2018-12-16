package com.example.android.agenda;

/**
 * Created by AL-MOMEN on 11/09/2018.
 */

public class DataClass {

     private String title,author,publisher,publish_date,description,imageurl;


    public DataClass(String title, String author, String publisher, String publish_date, String description, String imageurl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publish_date = publish_date;
        this.description = description;
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
