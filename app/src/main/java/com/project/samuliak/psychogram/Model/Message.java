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
    @SerializedName("tab_id")
    @Expose
    private Integer tabId;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Message(){}

    public Message(String text, Date creationDate, String sender, Integer tabId) {
        this.text = text;
        this.creationDate = creationDate;
        this.sender = sender;
        this.tabId = tabId;
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
}