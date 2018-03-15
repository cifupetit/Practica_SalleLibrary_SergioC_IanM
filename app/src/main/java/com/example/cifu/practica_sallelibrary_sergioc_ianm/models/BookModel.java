package com.example.cifu.practica_sallelibrary_sergioc_ianm.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by cifu on 03/03/2018.
 */

public class BookModel implements Parcelable {
    private String img, title , subtitle, description, publisher;
    private String[] authors;
    private String publishDate;
    private double price;

    public BookModel(String img, String title, String subtitle, String description, String publisher, String[] authors, String publishDate, double price) {
        this.img = img;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.publisher = publisher;
        this.authors = authors;
        this.publishDate = publishDate;
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getStringAuthors() {
        return Arrays.toString(authors);
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeString(this.description);
        dest.writeString(this.publisher);
        dest.writeString(this.authors.toString());
        dest.writeString(this.publishDate);
        dest.writeDouble(this.price);
    }

    public static final Creator<BookModel> CREATOR = new Creator<BookModel>() {
        @Override
        public BookModel createFromParcel(Parcel source) {
            return new BookModel(source);
        }

        @Override
        public BookModel[] newArray(int size) {
            return new BookModel[size];
        }
    };

    private BookModel(Parcel source) {
        this.img = source.readString();
        this.title = source.readString();
        this.subtitle = source.readString();
        this.description = source.readString();
        this.publisher = source.readString();
        this.authors = source.readString().split(",");
        this.publishDate = source.readString();
        this.price = source.readDouble();
    }
}
