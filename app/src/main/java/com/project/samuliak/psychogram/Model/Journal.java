package com.project.samuliak.psychogram.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Journal {

    @SerializedName("client")
    @Expose
    private String client;

    @SerializedName("note")
    @Expose
    private String note;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("creationDate")
    @Expose
    private Date creationDate;

    public Journal(){}

    public Journal(String client, String note, Date creationDate) {
        this.client = client;
        this.note = note;
        this.creationDate = creationDate;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
