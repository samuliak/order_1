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

    @SerializedName("full_name_doctor")
    @Expose
    public String full_doctor;

    @SerializedName("full_name_client")
    @Expose
    public String full_client;

    @SerializedName("id")
    @Expose
    public Integer id;

    public Tab(){}

    public Tab(String doctor, String client, String full_doctor, String full_client) {
        this.doctor = doctor;
        this.client = client;
        this.full_doctor = full_doctor;
        this.full_client = full_client;
    }

    protected Tab(Parcel in) {
        doctor = in.readString();
        client = in.readString();
        full_doctor = in.readString();
        full_client = in.readString();
        id = in.readInt();
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

    public String getFull_doctor() {
        return full_doctor;
    }

    public void setFull_doctor(String full_doctor) {
        this.full_doctor = full_doctor;
    }

    public String getFull_client() {
        return full_client;
    }

    public void setFull_client(String full_client) {
        this.full_client = full_client;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(doctor);
        dest.writeString(client);
        dest.writeString(full_doctor);
        dest.writeString(full_client);
        dest.writeInt(id);
    }
}