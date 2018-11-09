package com.example.wassim.testapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Article implements Serializable{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("external_link")
    @Expose
    private String external_link;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getExternal_link() {
        return external_link;
    }

    public void setExternal_link(String external_link) {
        this.external_link = external_link;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", external_link='" + external_link + '\'' +
                ", created='" + created + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }


}
