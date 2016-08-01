package com.project.samuliak.psychogram.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("creation_date")
    @Expose
    private Date creationDate;

    @SerializedName("sender")
    @Expose
    private String sender;

    @SerializedName("full_sender")
    @Expose
    private String full_sender;

    @SerializedName("tab_id")
    @Expose
    private Integer tabId;

    @SerializedName("id")
    @Expose
    private Integer id;

    public Message(){}

    public Message(String text, Date creation_date, int tab_id, String sender, String full) {
        this.text = text;
        this.creationDate = creation_date;
        this.tabId = tab_id;
        this.sender = sender;
        this.full_sender = full;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFull_sender() {
        return full_sender;
    }

    public void setFull_sender(String full_sender) {
        this.full_sender = full_sender;
    }
}