package com.project.samuliak.psychogram.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tab implements Parcelable {

    @SerializedName("doctor")
    @Expose
    public String doctor;
    @SerializedName("client")
    @Expose
    public String client;
    @SerializedName("id")
    @Expose
    public Integer id;

    public Tab(){}

    public Tab(String doctor, String client) {
        this.doctor = doctor;
        this.client = client;
    }

    protected Tab(Parcel in) {
        doctor = in.readString();
        client = in.readString();
    }

    public static final Creator<Tab> CREATOR = new Creator<Tab>() {
        @Override
        public Tab createFromParcel(Parcel in) {
            return new Tab(in);
        }

        @Override
        public Tab[] newArray(int size) {
            return new Tab[size];
        }
    };

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(doctor);
        dest.writeString(client);
    }
}