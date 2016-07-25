package com.project.samuliak.psychogram.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client implements Parcelable {

    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("surname")
    @Expose
    public String surname;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("age")
    @Expose
    public Integer age;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("interest")
    @Expose
    public String interest;
    @SerializedName("id")
    @Expose
    public Integer id;

    public Client(){}

    public Client(String login, String name, String surname, String password, Integer age, String country, String city, String interest) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.age = age;
        this.country = country;
        this.city = city;
        this.interest = interest;
    }

    protected Client(Parcel in) {
        login = in.readString();
        name = in.readString();
        surname = in.readString();
        password = in.readString();
        country = in.readString();
        city = in.readString();
        interest = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(password);
        dest.writeString(country);
        dest.writeString(city);
        dest.writeString(interest);
    }
}