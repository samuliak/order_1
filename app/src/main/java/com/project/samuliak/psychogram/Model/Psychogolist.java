package com.project.samuliak.psychogram.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Psychogolist implements Parcelable{

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("interest")
    @Expose
    private String interest;
    @SerializedName("place_of_work")
    @Expose
    private String placeOfWork;
    @SerializedName("university")
    @Expose
    private String university;
    @SerializedName("specialization")
    @Expose
    private String specialization;
    @SerializedName("competence")
    @Expose
    private String competence;
    @SerializedName("direction_of_work")
    @Expose
    private String directionOfWork;
    @SerializedName("online")
    @Expose
    private Boolean online;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Psychogolist(){}

    public Psychogolist(String login, String name, String surname, String password,
                        Integer age, String country, String city, String interest,
                        String placeOfWork, String university, String specialization,
                        String competence, String directionOfWork) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.age = age;
        this.country = country;
        this.city = city;
        this.interest = interest;
        this.placeOfWork = placeOfWork;
        this.university = university;
        this.specialization = specialization;
        this.competence = competence;
        this.directionOfWork = directionOfWork;
    }

    protected Psychogolist(Parcel in) {
        login = in.readString();
        name = in.readString();
        surname = in.readString();
        password = in.readString();
        country = in.readString();
        city = in.readString();
        interest = in.readString();
        placeOfWork = in.readString();
        university = in.readString();
        specialization = in.readString();
        competence = in.readString();
        directionOfWork = in.readString();
    }

    public static final Creator<Psychogolist> CREATOR = new Creator<Psychogolist>() {
        @Override
        public Psychogolist createFromParcel(Parcel in) {
            return new Psychogolist(in);
        }

        @Override
        public Psychogolist[] newArray(int size) {
            return new Psychogolist[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getDirectionOfWork() {
        return directionOfWork;
    }

    public void setDirectionOfWork(String directionOfWork) {
        this.directionOfWork = directionOfWork;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
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
        dest.writeString(login);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(password);
        dest.writeString(country);
        dest.writeString(city);
        dest.writeString(interest);
        dest.writeString(placeOfWork);
        dest.writeString(university);
        dest.writeString(specialization);
        dest.writeString(competence);
        dest.writeString(directionOfWork);
    }
}