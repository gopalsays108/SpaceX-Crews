package com.gopal.spacexcrews.modal;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crew_members")
public class CrewsModal {

    @NonNull
    @PrimaryKey
    private String id;
    private String name;
    private String agency;
    private String image;
    private String wikipedia;
    private String status;


    public CrewsModal(String name, String agency, String image,
                      String wikipedia, String status, String id) {
        this.name = name;
        this.agency = agency;
        this.image = image;
        this.wikipedia = wikipedia;
        this.status = status;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CrewsModal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", agency='" + agency + '\'' +
                ", image='" + image + '\'' +
                ", wikipedia='" + wikipedia + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}